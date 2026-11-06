# Parking Lot — LLD

> **Intent:** Model a multi-floor parking lot that accepts vehicles of different types,
> assigns the nearest available spot, issues a ticket on entry, and charges a fee on exit.
> Physical entry/exit barriers are modelled as `EntryGate` / `ExitGate`.

---

## The Problem (naive approach)

```
Client
  │
  ├─ Scans every floor manually for a free spot       ❌ no encapsulation
  ├─ Stores entry time in a local variable             ❌ lost on scope exit
  ├─ Hard-codes fee: if CAR → price * hours            ❌ change fee = edit caller
  ├─ Calls spot.setOccupied(false) directly            ❌ no thread safety
  └─ No concept of gates — client IS the gate          ❌ no physical modelling
```

No separation of concerns — adding a new vehicle type, fee rule, or gate touches multiple files. ❌

---

## The Solution

```
Vehicle arrives at EntryGate
  │
  └─► EntryGate.enter(vehicle)                        ← physical layer
            │
            └─► ParkingService.checkIn(vehicle)       ← logic layer
                      │
                      ├─► ParkingLot.findAvailableSpot(vehicleType)   ← Singleton
                      │         │
                      │         └─► ParkingFloor.findFreeSpot(spotType)
                      │                   │
                      │                   └─► ParkingSpot.park(vehicle)  ← thread-safe
                      │
                      └─► new Ticket(vehicle, spot, floor, entryTime)
                                └─► returned through gate to driver

Vehicle arrives at ExitGate (any gate — not locked to entry gate ✅)
  │
  └─► ExitGate.exit(ticketId)                         ← physical layer
            │
            └─► ParkingService.checkOut(ticketId)     ← logic layer
                      │
                      ├─► lookup Ticket by ticketId
                      ├─► ParkingSpot.free()                         ← spot available again
                      ├─► FeeStrategy.calculate(vehicleType, hours)  ← Strategy pattern
                      └─► new Receipt(ticket, exitTime, duration, fee)
                                └─► returned through gate to driver
```

---

## Class Diagram

```
              «Singleton»
              ParkingLot
   ┌────────────────────────────────┐
   │ - name: String                 │
   │ - floors:     List<Floor>      │──────────────────────────────────┐
   │ - entryGates: List<EntryGate>  │──────────────┐                   │
   │ - exitGates:  List<ExitGate>   │──────────┐   │                   │
   │                                │          │   │                   │
   │ + getInstance()                │          │   │            ParkingFloor
   │ + addFloor(floor)              │          │   │       ┌──────────────────────────┐
   │ + addEntryGate(gate)           │          │   └──────►│ - floorNumber: int       │
   │ + addExitGate(gate)            │          │           │ - spots: List<ParkingSpot>│
   │ + getEntryGate(id)             │          │           │                          │
   │ + getExitGate(id)              │          │           │ + findFreeSpot(SpotType) │
   │ + findAvailableSpot(type)      │          │           │ + countFreeSpots(type)   │
   │ + printAvailability()          │          │           └────────────┬─────────────┘
   └────────────────────────────────┘          │                        │ 1..*
                                               │                        ▼
        EntryGate                              │                  ParkingSpot
   ┌──────────────────────────┐                │           ┌──────────────────────────┐
   │ - gateId: String         │                │           │ - spotNumber: int        │
   │ - parkingService         │                │           │ - type: SpotType         │
   │                          │                │           │ - parkedVehicle: Vehicle │
   │ + enter(vehicle): Ticket │                │           │                          │
   └──────────────────────────┘                │           │ + isFree(): boolean      │
                                               │           │ + park(vehicle): boolean │
        ExitGate                               │           │ + free(): Vehicle        │
   ┌──────────────────────────┐                │           └──────────────────────────┘
   │ - gateId: String         │◄───────────────┘
   │ - parkingService         │
   │                          │
   │ + exit(ticketId): Receipt│
   └──────────────────────────┘

        ParkingService
   ┌──────────────────────────────┐
   │ - parkingLot: ParkingLot     │
   │ - feeStrategy: FeeStrategy   │──────────► «interface»
   │ - activeTickets: Map<id,     │             FeeStrategy
   │     Ticket>                  │         ┌──────────────────────┐
   │                              │         │ + calculate(         │
   │ + checkIn(vehicle): Ticket   │         │     VehicleType,     │
   │ + checkOut(ticketId): Receipt│         │     hours): double   │
   └──────────────────────────────┘         └──────────┬───────────┘
                                                        │ implements
                                             HourlyFeeStrategy
                                         ┌────────────────────────────┐
                                         │ TWO_WHEELER → ₹20 / hr     │
                                         │ CAR         → ₹40 / hr     │
                                         │ TRUCK       → ₹80 / hr     │
                                         └────────────────────────────┘

        Vehicle                                  Ticket
   ┌──────────────────────┐            ┌──────────────────────────────┐
   │ - licensePlate: Str  │            │ - ticketId: String (UUID)    │
   │ - type: VehicleType  │            │ - vehicle: Vehicle           │
   └──────────────────────┘            │ - spot: ParkingSpot          │
                                       │ - floorNumber: int           │
        VehicleType (enum)             │ - entryTime: LocalDateTime   │
   ┌──────────────────────┐            └──────────────────────────────┘
   │  TWO_WHEELER         │
   │  CAR                 │                    Receipt
   │  TRUCK               │       ┌──────────────────────────────────┐
   └──────────────────────┘       │ - ticket: Ticket                 │
                                  │ - exitTime: LocalDateTime        │
        SpotType (enum)           │ - durationHours: long            │
   ┌──────────────────────┐       │ - fee: double                    │
   │  SMALL  (two-wheeler)│       └──────────────────────────────────┘
   │  MEDIUM (car)        │
   │  LARGE  (truck)      │
   └──────────────────────┘
```

---

## Spot Type Mapping

```
  VehicleType          SpotType
  ───────────          ────────
  TWO_WHEELER   ──►    SMALL
  CAR           ──►    MEDIUM
  TRUCK         ──►    LARGE
```

---

## Check-In Flow (sequence)

```
 Demo        EntryGate      ParkingService     ParkingLot     ParkingFloor   ParkingSpot
  │               │                │               │               │              │
  │ enter(vehicle)│                │               │               │              │
  │──────────────►│                │               │               │              │
  │               │ checkIn(vehicle)               │               │              │
  │               │───────────────►│               │               │              │
  │               │                │ findAvailable │               │              │
  │               │                │ Spot(CAR)     │               │              │
  │               │                │──────────────►│               │              │
  │               │                │               │ findFreeSpot  │              │
  │               │                │               │ (MEDIUM)      │              │
  │               │                │               │──────────────►│              │
  │               │                │               │               │ scan spots   │
  │               │                │               │               │─────────────►│
  │               │                │               │               │  Spot-4 free │
  │               │                │               │               │◄─────────────│
  │               │                │               │ FloorSpotPair │              │
  │               │                │               │◄──────────────│              │
  │               │                │ FloorSpotPair │               │              │
  │               │                │◄──────────────│               │              │
  │               │                │ spot.park(vehicle)            │              │
  │               │                │───────────────────────────────────────────►  │
  │               │                │               │               │    true      │
  │               │                │◄──────────────────────────────────────────── │
  │               │                │ new Ticket(vehicle, spot, floor)             │
  │               │                │ store in activeTickets        │              │
  │               │    Ticket      │               │               │              │
  │               │◄───────────────│               │               │              │
  │  Ticket + log │                │               │               │              │
  │◄──────────────│                │               │               │              │
```

---

## Check-Out Flow (sequence)

```
 Demo        ExitGate       ParkingService    FeeStrategy      ParkingSpot
  │               │                │               │                │
  │ exit(ticketId)│                │               │                │
  │──────────────►│                │               │                │
  │               │ checkOut(ticketId)             │                │
  │               │───────────────►│               │                │
  │               │                │ lookup Ticket │                │
  │               │                │ compute exitTime, hours        │
  │               │                │ calculate(CAR, 2hrs)           │
  │               │                │──────────────►│                │
  │               │                │   ₹80.00      │                │
  │               │                │◄──────────────│                │
  │               │                │ spot.free()   │                │
  │               │                │───────────────────────────────►│
  │               │                │               │  spot now free │
  │               │                │◄───────────────────────────────│
  │               │                │ new Receipt(ticket,exitTime,2,₹80)
  │               │    Receipt     │               │                │
  │               │◄───────────────│               │                │
  │Receipt + log  │                │               │                │
  │◄──────────────│                │               │                │
```

---

## Cross-Gate Flow

```
  Vehicle enters Gate-A (north)          Vehicle exits Gate-Y (south)
  ──────────────────────────────         ──────────────────────────────
  entryA.enter(vehicle)                  exitY.exit(ticketId)
    └─► ParkingService.checkIn()           └─► ParkingService.checkOut()
          └─► Ticket (e.g. AB12)                 └─► Receipt printed ✅

  Any exit gate can process any ticket
  because all gates share the SAME ParkingService instance. ✅
  Driver is NOT locked to the gate they entered through.
```

---

## Fee Calculation

```
  Duration (minutes)   Ceiling hours   VehicleType   Fee
  ──────────────────   ─────────────   ───────────   ───────────────
       0 – 60 min    →    1 hr         TWO_WHEELER   1 × ₹20 = ₹20
      61 – 120 min   →    2 hrs        CAR           2 × ₹40 = ₹80
     121 – 180 min   →    3 hrs        TRUCK         3 × ₹80 = ₹240

  Minimum charge: 1 hour regardless of actual duration.
```

---

## Full Lot Scenario

```
  EntryGate.enter(TRUCK)
    └─► ParkingService.checkIn(TRUCK)
              └─► ParkingLot.findAvailableSpot(TRUCK)
                        │
                        └─► scan Floor-1 LARGE spots → all occupied
                        └─► scan Floor-2 LARGE spots → all occupied
                        └─► Optional.empty()
              └─► orElseThrow()
  ❌ IllegalStateException: "Parking lot is full for vehicle type: TRUCK"
     caught and logged at gate level
```

---

## Spot Re-Use After Free

```
  State: CAR parked at Floor-1 Spot-4 (MEDIUM)

  ExitGate.exit(ticketId)
    └─► ParkingService.checkOut()
          └─► spot.free()  →  Spot-4 parkedVehicle = null  ✅

  EntryGate.enter(new CAR)
    └─► ParkingService.checkIn()
          └─► findAvailableSpot(CAR)
                └─► Floor-1 findFreeSpot(MEDIUM)
                      └─► Spot-4 isFree() = true  ✅  ← re-assigned immediately
```

---

## Package Structure

```
parkinglot/
│
├── enums/
│   ├── VehicleType.java          TWO_WHEELER | CAR | TRUCK
│   └── SpotType.java             SMALL | MEDIUM | LARGE
│
├── model/
│   ├── Vehicle.java              licensePlate + type
│   ├── ParkingSpot.java          one physical space — thread-safe park() / free()
│   ├── ParkingFloor.java         holds spots, finds first free by SpotType
│   ├── Ticket.java               issued on entry (spot, floor, entryTime, UUID id)
│   ├── Receipt.java              issued on exit (duration, fee, formatted print)
│   ├── EntryGate.java            physical entry barrier → delegates to ParkingService
│   └── ExitGate.java             physical exit barrier  → delegates to ParkingService
│
├── strategy/
│   ├── FeeStrategy.java          interface — pluggable fee calculation
│   └── HourlyFeeStrategy.java    ₹20/hr (2W) · ₹40/hr (Car) · ₹80/hr (Truck)
│
├── service/
│   ├── ParkingLot.java           Singleton — owns floors + gates, finds available spot
│   └── ParkingService.java       checkIn() / checkOut() core logic
│
├── ParkingLotDemo.java           end-to-end demo entry point
└── PARKING_LOT.md                this file
```

---

## Key Roles

| Role | Class | Responsibility |
|------|-------|----------------|
| Lot manager | `ParkingLot` (Singleton) | Physical structure — floors, gates, spot search |
| Entry barrier | `EntryGate` | Accepts vehicle, delegates to service, issues Ticket |
| Exit barrier | `ExitGate` | Accepts ticket, delegates to service, issues Receipt |
| Floor | `ParkingFloor` | Holds spots, scans for free spot by type |
| Spot | `ParkingSpot` | One physical space — thread-safe allocation |
| Vehicle | `Vehicle` | License plate + vehicle type |
| Entry record | `Ticket` | Immutable snapshot of entry: spot, floor, time |
| Exit record | `Receipt` | Duration + fee summary |
| Orchestrator | `ParkingService` | Core logic — entry/exit, ticket store |
| Fee rule | `FeeStrategy` / `HourlyFeeStrategy` | Pluggable fee calculation (Strategy) |

---

## Design Patterns Used

| Pattern | Where | Why |
|---------|-------|-----|
| **Singleton** | `ParkingLot` | One physical lot per application |
| **Strategy** | `FeeStrategy` | Swap fee rules (hourly / flat / peak-hour) without touching `ParkingService` |
| **Facade** | `EntryGate` / `ExitGate` | Simplify the entry/exit interaction — driver talks to gate, not to service/lot directly |

---

## Key Rules

| Rule | Why |
|------|-----|
| `ParkingLot` is a Singleton | One instance represents the real building |
| Gates are the public interface; `ParkingService` is internal | Layered architecture — physical layer (gates) over logic layer (service) |
| Any exit gate processes any ticket | All gates share the same `ParkingService` — driver not locked to entry gate |
| `ParkingSpot.park()` / `free()` are `synchronized` | Thread-safe — multiple gates can operate concurrently |
| Fee logic is behind `FeeStrategy` interface | Add peak-hour or flat-rate pricing with zero service change |
| `Ticket` stores a direct `ParkingSpot` reference | `checkOut` frees the exact spot without a second search |
| `Ticket` and `Receipt` are separate immutable classes | SRP — ticket = proof of entry, receipt = proof of payment. Merging causes null fields + mutable state |
| Spot search: Floor 1 → Floor N, Spot 1 → Spot M | Deterministic — fills lower floors first |
| Minimum 1 hour charge (ceiling billing) | Standard real-world parking billing |

---

## Extending the Design

### Add a new gate

```
Step 1 — Instantiate
  EntryGate entryC = new EntryGate("Gate-C", service);   // new south-west entry

Step 2 — Register
  lot.addEntryGate(entryC);

ParkingService  → 0 changes ✅
ParkingLot      → 0 changes ✅
ParkingSpot     → 0 changes ✅
```

### Add a new vehicle type (e.g. BUS)

```
Step 1 — Add to VehicleType enum        BUS
Step 2 — Add to SpotType enum           EXTRA_LARGE  (if new size needed)
Step 3 — Map in ParkingLot.toSpotType() case BUS -> EXTRA_LARGE
Step 4 — Add rate in HourlyFeeStrategy  case BUS -> 120.0   // ₹120/hr

EntryGate / ExitGate → 0 changes ✅
ParkingService       → 0 changes ✅
ParkingFloor         → 0 changes ✅
ParkingSpot          → 0 changes ✅
```

### Add a new fee strategy (e.g. peak-hour pricing)

```
Step 1 — Create PeakHourFeeStrategy implements FeeStrategy
  @Override calculate(vehicleType, hours) { ... }

Step 2 — Inject into ParkingService
  new ParkingService(lot, new PeakHourFeeStrategy())

EntryGate / ExitGate  → 0 changes ✅
HourlyFeeStrategy     → untouched ✅
ParkingService        → 0 changes ✅
```

---

## Real-World Analogies

| Real system | Maps to |
|-------------|---------|
| Parking building | `ParkingLot` (Singleton) |
| Each level / deck | `ParkingFloor` |
| Painted bay on the floor | `ParkingSpot` |
| Barrier arm + ticket printer at entrance | `EntryGate` |
| Barrier arm + payment terminal at exit | `ExitGate` |
| Paper ticket given at entry | `Ticket` |
| Receipt printed after payment | `Receipt` |
| Parking management software | `ParkingService` |
| Rate board at entrance | `FeeStrategy` |
