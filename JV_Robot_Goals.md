# JV Robot Goals (BIOBUZZ 2026-2027)

Status: **Draft v0.1**. Derived from `JV_Requirements.md` (IDs in brackets). Check off goals as they are met and fill in `TBD` values after practice data.

Scoring reference: LEAVE 3 (AUTO), PARK 5, HIVE TIP 20, element in CELL 2, GARDEN 1, owned FLOWER 2 per element, Bottom NECTAR bonus 5.

---

## Autonomous (30 s)

### Goals

| # | Goal | Points | Priority | Done |
|---|---|---|---|---|
| A1 | LEAVE: move off the perimeter wall [JV-P2] | 3 | Must | [ ] |
| A2 | PARK: finish at least partially in own LOADING ZONE [JV-P2] | 5 | Must | [ ] |
| A3 | Launch the 4 pre-loaded POLLEN toward the CELL [JV-P6] | up to 20 (1 TIP) | Could | [ ] |

### Targets

- Reliability: A1 + A2 succeed in at least 9 of 10 practice runs.
- Target AUTO score: 8 points (LEAVE + PARK). Stretch: 28 (with 1 TIP).
- Start positions supported: `TBD` (red and blue, one start each at minimum).

### Constraints

- Robot starts touching exactly 4 POLLEN, motionless, in 18 in cube, on own side (G304).
- Stay on own side of the field (columns A-C red, D-F blue) (G402).
- Default no-op AUTO that auto-loads TELEOP must exist (G305).
- Nothing moves in the 8 s transition (G403).

### Approach

- Encoder or timed drive. IMU heading correction is a stretch goal [JV-S3].
- Separate red and blue OpModes, or one with an alliance selector `TBD`.

### Open Items

- [ ] Choose start position(s)
- [ ] Decide whether A3 is attempted this season

---

## TeleOp (2:00)

### Goals

| # | Goal | Points | Priority | Done |
|---|---|---|---|---|
| T1 | Drive reliably around the field (mecanum or tank) [JV-R3] | - | Must | [ ] |
| T2 | Collect POLLEN and hold no more than 4 at a time [JV-R4] | - | Must | [ ] |
| T3 | Deliver POLLEN to own GARDEN [JV-P3] | 1 each | Must | [ ] |
| T4 | End the match PARKED in the LOADING ZONE [JV-P4] | 5 | Must | [ ] |
| T5 | Launch into the upward-facing CELL for a HIVE TIP [JV-P5] | 20 | Should | [ ] |
| T6 | Place POLLEN into a FLOWER from the top, last 60 s only [JV-P7] | 2 each if owned | Could | [ ] |

### Targets

- Typical TELEOP score: `TBD` (set after first practice matches).
- Reach SWARM RP (16 LEAVE + PARK points) in most matches [JV-P8]. Note this needs AUTO and TELEOP PARK combined with other points, so confirm the total feasible.
- Launcher: at least 5 of 10 launches in the CELL from a fixed spot [M4].

### Constraints

- Control at most 4 elements (G407). Do not control opponent NECTAR (G408).
- No catching elements from a tipping HIVE (G409). No pushing the HIVE (G417).
- NECTAR into a FLOWER only with 60 s or less left (G410).
- Motionless after TELEOP ends (G404).

### Controls and Driver Aids

- One TELEOP OpMode with a one-page gamepad cheat sheet [JV-S1].
- Endgame logic gated by the match timer [JV-S7].
- Driver aids: `TBD` (e.g. launcher ready indicator).

### Open Items

- [ ] Choose launcher type (flywheel vs arm) [JV-R5]
- [ ] Assign driver and human player roles
