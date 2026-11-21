# Varsity Robot Goals (BIOBUZZ 2026-2027)

Status: **Draft v0.1**. Derived from `Varsity_Requirements.md` (IDs in brackets). Check off goals as they are met and fill in `TBD` values after practice data.

Scoring reference: LEAVE 3 (AUTO), PARK 5, HIVE TIP 20, element in CELL 2, GARDEN 1, owned FLOWER 2 per element, Bottom NECTAR bonus 5.

---

## Autonomous (30 s)

### Goals

| # | Goal | Points | Priority | Done |
|---|---|---|---|---|
| A1 | LEAVE [V-P2] | 3 | Must | [ ] |
| A2 | PARK in own LOADING ZONE [V-P2] | 5 | Must | [ ] |
| A3 | Launch the 4 pre-loaded POLLEN into the CELL for 1 HIVE TIP [V-P2] | 20 | Must | [ ] |
| A4 | Collect more elements and score a 2nd or later TIP [V-P3] | 20 each | Should | [ ] |
| A5 | Leave the robot positioned for a fast TELEOP cycle | - | Could | [ ] |

### Targets

- Reliability: A1-A3 succeed in at least 9 of 10 runs on both sides.
- Target AUTO score: 28 (LEAVE + PARK + 1 TIP). Stretch: 48+ (2 TIPS).
- Start positions supported: `TBD` (red and blue, selectable) [V-S4].
- Contributes to SWARM RP (16 LEAVE + PARK points) with TELEOP PARK.

### Constraints

- Starts touching exactly 4 POLLEN, motionless, in 18 in cube (G304).
- Stay on own side (columns A-C red, D-F blue) (G402). Coordinate with alliance partner.
- No contact with the HIVE other than launched elements (G417). Do not catch falling elements (G409).
- Default fallback OpMode if vision fails [V-S10]. Nothing moves in the 8 s transition (G403).

### Approach

- Odometry + IMU localization [V-S1]. Dead-wheel kit allowed (R303).
- AprilTag aiming on CELL tags (red 30-37, blue 38-45), single webcam or Limelight 3A [V-S2].
- Velocity-controlled launcher with ready-to-fire check [V-S3].

### Open Items

- [ ] Choose start positions and routes
- [ ] Decide vision hardware (UVC webcam vs Limelight 3A)
- [ ] Define alliance coordination plan

---

## TeleOp (2:00)

### Goals

| # | Goal | Points | Priority | Done |
|---|---|---|---|---|
| T1 | Field-centric holonomic drive [V-R3, V-S1] | - | Must | [ ] |
| T2 | Intake POLLEN and NECTAR, index at most 4, reject opponent NECTAR [V-R4] | - | Must | [ ] |
| T3 | Sustained cycle: collect, aim, launch, TIP, 4 or more TIPS per match [V-P4] | 20 per TIP | Must | [ ] |
| T4 | 7 or more TIPS in good matches (POLLINATOR 2 RP) [V-P5] | 20 per TIP | Should | [ ] |
| T5 | Endgame (last 60 s): own-color NECTAR on top of FLOWERS to own them [V-P7] | 2 per element + 5 bonus | Must | [ ] |
| T6 | GARDEN scoring when no better cycle is available [V-P8] | 1 each | Could | [ ] |
| T7 | End PARKED in the LOADING ZONE [V-P9] | 5 | Must | [ ] |
| T8 | Hold up under defense without penalties (G419-G421) [V-P11] | - | Should | [ ] |

### Targets

- TIPS per match: 4 (RP threshold) with a stretch of 7 [V-P4, V-P5].
- Target match score: `TBD` after first practice data [V-P10].
- Launcher accuracy: 8 of 10 in CELL from 3 or more field positions [M3].
- FLOWER placement: 8 of 10 attempts [M5].
- Ranking points: aim for SWARM, POLLINATOR 1 and POLLINATOR 2 each match.

### Constraints

- Control at most 4 elements (G407). Never control opponent NECTAR (G408).
- No catching mechanism for a tipping HIVE (G409). No pushing the HIVE (G417).
- FLOWER: enter only through the top, remove only POLLEN from the bottom (G418). NECTAR only with 60 s or less left (G410).
- No grabbing or hanging on arena elements (G415). Motionless after TELEOP ends (G404).

### Controls and Driver Aids

- One-button aim and spin-up, auto-park, 4-element intake limit [V-S6].
- Endgame mode gated by the match timer [V-S7].
- Driver readouts through Driver Station telemetry only [V-S9].

### Open Items

- [ ] Drivetrain choice (mecanum vs swerve)
- [ ] FLOWER NECTAR placement mechanism design
- [ ] Driver and human player assignments
