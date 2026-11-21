# Varsity Team: Initial Requirements (BIOBUZZ 2026-2027)

Status: **Draft v0.1**. This document has two sections:

1. **Competition Requirements**: what the manual requires of any robot and team (shared with the JV document).
2. **Team Requirements**: what the Varsity team intends to build and achieve. These are *proposed starting points* for the team to confirm or change. Items marked `TBD` need a team decision.

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

## Section 2: Varsity Team Requirements (proposed)

**Philosophy:** compete for advancement. Target full-field scoring (HIVE TIPS, FLOWER ownership, GARDEN, PARK), strong AUTO, and consistent play under defense. Use custom fabrication where it gives a real advantage, but keep every design inside the R-rules.

### 2.1 Performance Goals

| ID | Requirement | Priority |
|---|---|---|
| V-P1 | Pass inspection on the first attempt at every event | Must |
| V-P2 | AUTO: LEAVE (3), PARK (5) and at least 1 HIVE TIP (20), reliably (9 of 10 runs) | Must |
| V-P3 | AUTO: 2 or more HIVE TIPS, using the 4 pre-loaded POLLEN plus collected elements | Should |
| V-P4 | TELEOP: sustained scoring cycle of collect, launch, and TIP, targeting 4 or more TIPS per match (POLLINATOR 1 RP threshold) | Must |
| V-P5 | TELEOP: 7 or more TIPS per match in good matches (POLLINATOR 2 RP threshold) | Should |
| V-P6 | Reach SWARM RP (16 LEAVE + PARK points) in most matches | Should |
| V-P7 | Endgame (last 60 s): place own-color NECTAR on top of FLOWERS to take ownership, capturing the 2 pts per element and 5 pt Bottom NECTAR bonus where possible | Must |
| V-P8 | Score POLLEN in the GARDEN (1 pt each) when no better cycle is available | Could |
| V-P9 | End TELEOP PARKED in the LOADING ZONE (5 pts) | Must |
| V-P10 | Typical target match score: `TBD` (set after the first practice-match data) | `TBD` |
| V-P11 | Play effective defense and withstand defense without violating G419-G421 | Should |

### 2.2 Robot Design Requirements

| ID | Requirement | Notes |
|---|---|---|
| V-R1 | Starting configuration of 18 x 18 x 18 in or less with measured margin | R102 |
| V-R2 | Extensions limited mechanically to 18 x 24 x 29 in. Verified in every configuration, including a measured check of vertical reach | R105 |
| V-R3 | Holonomic drivetrain (mecanum or swerve), robust enough for high-contact play | R301, R303 |
| V-R4 | Intake holds and indexes no more than 4 elements, with a physical or sensor guard. Rejects opponent NECTAR | G407, G408 |
| V-R5 | Launcher reaches the CELL opening (bottom 53.5 in, top 65.6 in above floor, 20 in wide) from multiple field positions. Adjustable speed or angle | G417, G405 |
| V-R6 | Launcher does not strike the HIVE frame or underside, does not contact the HIVE other than by launched elements, and does not eject elements out of the field | G405, G417 |
| V-R7 | NECTAR placement mechanism for the FLOWER top opening (~4 in, 21.5 in high). Must be able to place the top-most NECTAR | G418, G410 |
| V-R8 | No mechanism that opens wide to catch elements falling from a tipping HIVE | G409 |
| V-R9 | Optional concave alignment feature around a FLOWER. No grabbing or hanging | G415 |
| V-R10 | Motors and servos within 8 and 8, with all motors from the Table 12-1 list. Servos checked against the R502 power and stall limits | R501-R503 |
| V-R11 | Frame and electronics protected: power switch shielded, wiring covered, no exposed delicate intake parts that invite damage claims | R603, G419 |
| V-R12 | Two or more ROBOT SIGNS, red and blue, robustly mounted | R401-R403 |
| V-R13 | Electrical system built to R601-R613, and inspection-ready (diagnostic lights visible) | R606 |
| V-R14 | Odometry (dead-wheel kit allowed) for AUTO positioning | R303 exception |
| V-R15 | Spare drivetrain parts and a second battery set ready for fast swaps between matches | |

### 2.3 Software and Control Requirements

| ID | Requirement | Notes |
|---|---|---|
| V-S1 | Odometry plus IMU localization for AUTO and field-centric TELEOP drive | |
| V-S2 | AprilTag vision on the CELL tags (IDs 30-33 / 34-37 red, 38-41 / 42-45 blue) for launcher aiming. Single UVC webcam or Limelight 3A | R702, R707, R708 |
| V-S3 | Launcher control: velocity PID or feedforward, with a ready-to-fire indicator for the driver | See `Flywheel_RPM.java` |
| V-S4 | Multiple AUTO routines: red and blue sides, with selectable start position and strategy | G304, G402 |
| V-S5 | AUTO stays on own side of the field (columns A-C red, D-F blue) unless safe | G402 |
| V-S6 | Auto-assists in TELEOP: one-button aim and spin-up, auto-park, and 4-element intake limit | G407 |
| V-S7 | Endgame mode: gated by the match timer so NECTAR enters a FLOWER only with 60 s or less remaining | G410 |
| V-S8 | All motion halted during the AUTO to TELEOP transition and after the match ends | G403, G404 |
| V-S9 | Telemetry via Driver Station only. No Dashboard or Panels streaming at events. Logs written to local storage and reviewed offline | R704.D |
| V-S10 | Default and fallback OpModes so a vision failure does not disable the robot | G305 |
| V-S11 | Code reviewed before event tags. Regression test on the practice field after each change | |

### 2.4 Process and Team Requirements

| ID | Requirement |
|---|---|
| V-T1 | Formal design review (CAD) for the drivetrain, intake, launcher and FLOWER placement before fabrication |
| V-T2 | Robot driving by the end of week 2 of build, with iterative improvement afterward (`TBD` dates) |
| V-T3 | Full practice field (or tile mockup with a HIVE and FLOWER) available for testing. Document in-spec dimensions from the CAD model |
| V-T4 | Log data for every practice match: score, cycles, failures. Review weekly |
| V-T5 | Defined drive team: 2 drivers, 1 human player (NECTAR introduction through the LOADING ZONE only), 1 coach. Run at least 10 full practice matches with all roles before the first event |
| V-T6 | Scouting plan for qualification matches and alliance selection (including ALLIANCE partner AUTO coordination to avoid G402 conflicts) |
| V-T7 | Pre-match checklist, pit crew roles, and a rapid repair plan (spare parts list) |
| V-T8 | Engineering notebook and judged-award materials maintained (see manual Section 6, Awards) |
| V-T9 | Coordinate with JV: share code libraries, mentor JV members, and avoid duplicating purchases `TBD` |
| V-T10 | Follow Gracious Professionalism, Coopertition and the Competition Integrity Contract (G201, G202) |

### 2.5 Acceptance and Milestones

| Milestone | Exit criteria | Target date |
|---|---|---|
| M1: Drivable chassis + odometry | Field-centric drive works. Odometry drift within `TBD` over a full-field run | `TBD` |
| M2: Intake + indexing | Picks up POLLEN and NECTAR, never holds more than 4, rejects opponent NECTAR | `TBD` |
| M3: Launcher | 8 of 10 launches in the CELL from at least 3 field positions | `TBD` |
| M4: AUTO | 9 of 10 successful runs of LEAVE + PARK + 1 TIP on both sides | `TBD` |
| M5: FLOWER placement | Places NECTAR on top of a FLOWER in 8 of 10 attempts | `TBD` |
| M6: Event-ready | Pre-inspection pass, 10 practice matches, spares packed | `TBD` |

### 2.6 Open Questions

- Which events and qualifying path apply, and what are the dates?
- Does the team own a full BIOBUZZ field or a mockup? (Field resources are on the FIRST Playing Field Resources page.)
- Vision hardware: UVC webcam or Limelight 3A?
- Drivetrain choice (mecanum or swerve), and which COTS kits are already owned?
- Budget and fabrication resources (3D printer, CNC, machine shop access)?
