# JV Team: Initial Requirements (BIOBUZZ 2026-2027)

Status: **Draft v0.1**. This document has two sections:

1. **Competition Requirements**: what the manual requires of any robot and team (shared with the Varsity document).
2. **Team Requirements**: what the JV team intends to build and achieve. These are *proposed starting points* for the team to confirm or change. Items marked `TBD` need a team decision.

---

## Section 1: Competition Requirements (BIOBUZZ, Manual TU05)

Source: *2026-2027 FIRST Tech Challenge Competition Manual, BIOBUZZ presented by RTX*, version TU05. Rule IDs (G = game, R = robot) refer to that manual. This section is identical for both teams. Rules marked *TBD* are not final in TU05 (e.g. RP thresholds for championships).

### 1.1 Game Summary

| Item | Requirement |
|---|---|
| Format | 2 ALLIANCES of 2 teams. Match = 30 s AUTO, 8 s transition, 2:00 TELEOP |
| Objective | Collect POLLEN (yellow, ~2.8 in) and NECTAR (red/blue, ~3.6 in). LAUNCH them into the HIVE CELLS to TIP the HIVE, and place them into FLOWERS and the GARDEN |
| Field | 144 x 144 in, 36 soft-foam TILES (24 in). Red ALLIANCE AREA on the left from the audience view |
| Scoring elements | 40 POLLEN, 8 red NECTAR, 8 blue NECTAR per match |
| Starting pre-load | Each ROBOT starts touching exactly 4 POLLEN (G304.G) |
| Staging | Red ROBOT on field columns A-C, blue on D-F; touching the perimeter wall; outside the LOADING ZONE; not in contact with a FLOWER |

### 1.2 Scoring

| Achievement | AUTO | TELEOP |
|---|---|---|
| LEAVE (no longer touching the perimeter wall) | 3 | - |
| PARK (at least partially in own LOADING ZONE) | 5 | 5 |
| HIVE TIP | 20 | 20 |
| POLLEN/NECTAR remaining in upward-facing CELL (end of match) | - | 2 each |
| Bottom NECTAR Bonus (FLOWER) | - | 5 |
| POLLEN/NECTAR in an owned FLOWER | - | 2 each |
| POLLEN/NECTAR in GARDEN | - | 1 each |

- **FLOWER ownership:** the ALLIANCE whose NECTAR is top-most in a FLOWER owns it and scores for every element in it. FLOWER scoring is allowed only in the last 60 s (G410).
- **HIVE TIP:** only valid by LAUNCHING into the upward-facing CELL (G417). Each TIP lets the ALLIANCE introduce one more NECTAR; all remaining NECTAR may be introduced at 60 s or less remaining (G426).
- **Ranking Points:** Win = 3, Tie = 1. SWARM RP = 16 LEAVE+PARK points. POLLINATOR 1 RP = 4 TIPS. POLLINATOR 2 RP = 7 TIPS. (Thresholds for "all other events"; championship thresholds *TBD*.)

### 1.3 Robot Construction Requirements (R rules)

**Size and design**
- R101: Robot and its major mechanisms are built by the registered team.
- R102: Starting configuration fits an 18 x 18 x 18 in cube, fully stationary and self-supported (R103). Pre-loaded elements may extend outside.
- R105 / G416: After start, may expand but must stay inside 18 x 24 x 29 in (29 in vertical). Limit must be enforced mechanically, not by software. No deliberately detached parts. Must remain one assembly.
- R104: No weight limit.
- R201: No damage or mess: no sharp edges, loose ballast, excess lubricant or TILE-damaging traction.
- R202: Safe, fair-play design: no vision shields, no imagery mimicking 36h11 AprilTags, no lighting that flashes above 5 Hz, no devices designed to flip or damage robots.
- R203: Robot and scoring elements removable from the field while powered off.
- R204: No mechanisms that grab the floor or create suction.
- R305: No scoring elements or replicas used in construction.

**Fabrication and COTS**
- R301: COTS major mechanisms built for a game task are prohibited, except a COTS drive chassis and official StarterBot mechanisms.
- R303: COTS parts must be single degree of freedom (slide kits, single-speed gearboxes, and similar). Exceptions include omni/mecanum wheels and dead-wheel odometry kits.
- R302 / R304: Raw materials and legal COTS may be modified. Pre-kickoff software, designs and fabricated items may be reused.

**Robot signs**
- R401-R403: At least 2 ROBOT SIGNS at 90 degrees or more apart, at least 6.5 x 2.5 in, robust and supported by the frame. Solid red or blue opaque background for ALLIANCE color. White Arabic team number about 2.25 in tall, not vertically stacked, not powered or illuminated.

**Actuators**
- R501: Only approved motors (e.g. goBILDA, REV HD Hex / Core Hex, NeveRest, TETRIX, Studica, WATTOS; see manual Table 12-1).
- R502: Servos at or below 8 W mechanical output and 4 A stall at 6 V. Linear servos at or below 1 A stall at 6 V.
- R503: At most 8 motors and 8 servos total across all configurations.
- R504: No modifying motors or servos except as listed.
- R505: Actuators driven only through approved power-regulating devices (Table 12-3). Limits per port apply (2 motors or 2 servos).
- R506: No relays, electromagnets or solenoids.
- R801: No pneumatic actuators, compressors, vacuums or high-speed blowers (sealed gas springs and dampers allowed).

**Power**
- R601: Exactly one approved 12 V NiMH main battery (Table 12-4).
- R603: One approved main power switch (Table 12-5), accessible and protected from robot-to-robot contact.
- R604: Unmodified fuses.
- R605: Frame is not a current path. Grounding only via an approved strap (Table 12-6).
- R606: Electrical system, fuses and controller diagnostic lights must be inspectable.
- R607 / R612: Custom circuits at or below 5 V regulated (LEDs excepted). No altering critical power paths.
- R608 / R613: Power devices only via their specified ports. No cross-wiring ports.
- R609: Minimum wire gauge: 18 AWG for battery and motor power and 11-20 A circuits. 22 AWG for servo, LED and 10 A or lower circuits. 28 AWG for signal.
- R610: 12 V and 5 V bus wires color-coded (positive: red/yellow/white/brown/black-with-stripe; negative: black/blue).
- R602 / R611: Other batteries only for self-contained peripherals (100 Wh or less) and powered USB hubs. They must not power actuators.

**Control and communications**
- R701: One ROBOT CONTROLLER: REV Control Hub, or Android phone with a REV Expansion Hub. At most one additional Expansion Hub.
- R702: No altering coprocessor software. The Limelight 3A is the only supported programmable vision coprocessor.
- R704: Official Wi-Fi only. Laptops disconnected during matches. No FTC Dashboard or Panels streaming, no video streams. Telemetry only via the Driver Station app.
- R705: Devices named `<team#>-RC` and `<team#>-DS`.
- R706: No tampering with the Control Hub, Driver Station, power regulators, switches, fuses or batteries beyond permitted exceptions.
- R707 / R708: USB limited to single-sensor UVC webcams, supported vision devices, hubs and the Expansion Hub.
- R710: Lasers only if part of a Class 1, non-visible sensor.
- R711: Control Hub Wi-Fi password changed from default. Bluetooth off, Wi-Fi on, remembered Wi-Fi Direct groups removed.

**Operator console**
- R901-R904: One approved Driver Station device (REV Driver Hub or Android). Touch screen accessible. Console fits in 3 ft x 1.5 ft x 2 ft. Wireless limited to the Robot Controller app link (no Bluetooth gamepads).

### 1.4 Game Rules the Robot and Software Must Respect

| Rule | Requirement | Design / software implication |
|---|---|---|
| G407 | Control at most 4 scoring elements at once | Intake and storage capped at 4 (guards or sensor) |
| G408 | Do not control opponent NECTAR | Color sort or reject opponent NECTAR |
| G409 | No catching elements released by a TIPPING HIVE | No wide-open catching mechanism at the top of the robot |
| G410 | NECTAR into FLOWERS only in the last 60 s | Endgame timer logic in TELEOP |
| G415 | No grabbing, grasping, attaching to, or hanging from arena elements | Concave FLOWER alignment is allowed |
| G417 | HIVE moved only by LAUNCHING into the upward-facing CELL | No contacting or pushing the HIVE |
| G418 | FLOWERS: enter only through the top, remove only POLLEN from the bottom | Retrieval opening is POLLEN only |
| G405 | Do not deliberately eject elements from the field | Launcher aiming and safety |
| G419-G421 | No damaging, tipping or entangling robots. 3-second PIN limit | Robust drivetrain, no wedges |
| G401 / G403 / G404 | No driver input in AUTO. No powered movement in the transition or after TELEOP ends | OpMode must be idle in the transition |
| G304 / G305 | Robot motionless after INIT. An OpMode must be selected and INITed (30 s timer enabled for AUTO) | Provide a default AUTO OpMode that auto-loads TELEOP |
| G412 | Robot under control, nothing contacting people outside the field | Launcher power and aim bounded |

AUTO scoring opportunities: LEAVE (3), PARK (5), HIVE TIPS (20 each), launched elements. A robot may not interfere with the opposing ALLIANCE'S side in AUTO (G402).

### 1.5 Field Reference (for sensing, targeting and path planning)

| Feature | Dimension |
|---|---|
| HIVE CELL opening | 20 in wide x 14 in tall x 12 in deep |
| CELL opening height above TILES | bottom 53.5 in, top 65.6 in |
| Hive centers | 25.5 in apart (red and blue) |
| FLOWER top opening | ~4 in diameter, ~21.5 in above TILES, with a 1.25 in backstop |
| FLOWER retrieval opening | ~3.55 in tall x 3.57 in wide |
| FLOWER bottom ring | ~2.79 in diameter hole |
| GARDEN | ~23 x 2 in strip in opposite corners |
| LOADING ZONE | ~23 x 11 in |
| APRILTAGS (36h11, 3.25 in) | Red 30-33 (opposite side), 34-37 (audience side). Blue 38-41 (audience side), 42-45 (opposite side). Tags face down on the underside of each CELL |

Field dimensions carry about +/- 1 in tolerance. Scoring elements vary in size and are not perfectly round. Designs must tolerate this.

### 1.6 Event Logistics

- **Drive team:** up to 4 people. 1 DRIVE COACH (may be an adult), plus up to 3 students (DRIVER, DRIVER, HUMAN PLAYER). Badges worn above the waist.
- **Human player:** introduces own ALLIANCE NECTAR only through the LOADING ZONE, without tools (G426, G427). Drive team stays in the ALLIANCE AREA (G422). Coaches may not handle gamepads or scoring elements (G423, G424).
- **Penalties:** Minor foul = 5 points to the opponent. Major foul = 20 points. Yellow card is a warning. A second yellow becomes a red card, which means disqualification from that match.
- **Inspection:** robot must pass inspection and be re-inspected after major changes (G303). A drive team member must attend each qualification match (G203).
- **Conduct:** Gracious Professionalism, Coopertition and the Competition Integrity Contract apply (G201, G202).

---

## Section 2: JV Team Requirements (proposed)

**Philosophy:** build a reliable, scorable robot first, then add capability. Prefer simple, proven mechanisms, and prefer COTS drivetrain and StarterBot-style designs (allowed by R301) to custom fabrication. Learning and consistency outrank peak score.

### 2.1 Performance Goals

| ID | Requirement | Priority |
|---|---|---|
| JV-P1 | Pass inspection on the first attempt at every event | Must |
| JV-P2 | AUTO: LEAVE (3 pts) and PARK (5 pts), reliably, at least 9 of 10 practice runs | Must |
| JV-P3 | TELEOP: collect POLLEN and score into the GARDEN (1 pt each) | Must |
| JV-P4 | TELEOP: end the match PARKED in the LOADING ZONE (5 pts) | Must |
| JV-P5 | Launch at least 1 element into the upward-facing CELL to earn a HIVE TIP (20 pts) in TELEOP | Should |
| JV-P6 | AUTO: launch the 4 pre-loaded POLLEN toward the CELL | Could |
| JV-P7 | Place POLLEN into a FLOWER from the top in the last 60 s | Could |
| JV-P8 | Reach the SWARM RP threshold (16 LEAVE + PARK points, combined AUTO and TELEOP) in most matches | Should |

### 2.2 Robot Design Requirements

| ID | Requirement | Notes |
|---|---|---|
| JV-R1 | Starting configuration of 18 x 18 x 18 in or less with margin (target 17.5 in) | R102 |
| JV-R2 | Mechanical limit stops keep extensions inside 18 x 24 x 29 in | R105 |
| JV-R3 | Mecanum or tank drivetrain from a COTS chassis kit | R301.A, R303 |
| JV-R4 | Single intake that physically holds no more than 4 elements | G407 |
| JV-R5 | Simple flywheel or arm launcher aimed at a CELL (opening 20 x 14 in, bottom 53.5 in above floor) | `TBD`: choose flywheel vs arm |
| JV-R6 | Motor and servo count at or below 8 each, with at least 1 of each spare | R503 |
| JV-R7 | Two robust ROBOT SIGNS in red and in blue, reversible without exposing the wrong color | R401-R403 |
| JV-R8 | Battery, switch, fuse and Control Hub lights accessible and labeled for inspection | R606 |
| JV-R9 | Wiring: correct gauge and color coding, strain relief on all connectors | R609, R610 |
| JV-R10 | No sharp edges or loose parts. Robot is tested with scoring elements for damage | R201 |
| JV-R11 | Robot can be lifted off the field with power off | R203 |

### 2.3 Software and Control Requirements

| ID | Requirement | Notes |
|---|---|---|
| JV-S1 | One TELEOP OpMode, with gamepad mapping documented on a one-page cheat sheet | |
| JV-S2 | At least one AUTO OpMode (LEAVE + PARK) and a default no-op AUTO that auto-loads TELEOP | G305 |
| JV-S3 | AUTO motion by encoder or timed drive. IMU heading correction is a stretch goal | |
| JV-S4 | Telemetry limited to the Driver Station app. No Dashboard or Panels streaming at events | R704.D |
| JV-S5 | Devices named `<team#>-RC` and `<team#>-DS`. Control Hub Wi-Fi password changed | R705, R711 |
| JV-S6 | Code stored in the team repository, with a tag for each event build | |
| JV-S7 | Launcher and endgame logic must not run before 60 s remain when NECTAR is involved | G410 |
| JV-S8 | Vision (AprilTag) is optional. Evaluate only after JV-P2 to JV-P4 are met | Could |

### 2.4 Process and Team Requirements

| ID | Requirement |
|---|---|
| JV-T1 | Complete a design review for the intake and launcher before building |
| JV-T2 | Run at least 5 full practice matches (AUTO + TELEOP) with a human player before the first event |
| JV-T3 | Assign and rotate roles: driver, second driver or human player, coach, pit crew. Every member drives at least once |
| JV-T4 | Keep an engineering notebook entry for each build session |
| JV-T5 | Pre-match checklist, including wiggle test, OpMode INIT and 4 POLLEN pre-loaded |
| JV-T6 | Bring spares: battery, Control Hub, motor, servo, wire and fasteners |
| JV-T7 | Follow Gracious Professionalism and the Competition Integrity Contract (G201, G202) |

### 2.5 Acceptance and Milestones

| Milestone | Exit criteria | Target date |
|---|---|---|
| M1: Drivable chassis | Drives, strafes and turns under TELEOP control. Inspection-ready wiring | `TBD` |
| M2: Intake + storage | Picks up POLLEN and holds at most 4 | `TBD` |
| M3: AUTO LEAVE + PARK | 9 of 10 successful runs | `TBD` |
| M4: Launcher | At least 5 of 10 launches land in the CELL from a fixed spot | `TBD` |
| M5: Event-ready | Pre-inspection pass, 5 practice matches, spares packed | `TBD` |

### 2.6 Open Questions

- Which event dates and qualifying path apply to JV?
- Does JV share a robot, parts or a practice field with Varsity?
- What budget is available for COTS kits and parts?
- Which Control Hub, motors and batteries does the team already own?
