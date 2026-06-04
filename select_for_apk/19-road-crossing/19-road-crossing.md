# Street Hopper — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name (Frogger), logo, or copyrighted art/audio/text. The clone is named
> **Street Hopper** with original characters and branding.

---

## 1. Snapshot

Street Hopper is a 2D top-down arcade game in which the player hops a small creature across a multi-lane road filled with moving vehicles, then across a river by leaping onto floating logs and slow-moving platforms, and finally into goal slots at the top of the screen. Reaching all goal slots advances the level; getting hit by a car, falling into water, or timing out costs a life. The original inspiration (Frogger, Konami/Sega, 1981) is one of the best-selling and most-ported arcade titles of all time, instantly recognizable from its core tension of precise timing against chaotic traffic.

**Quick facts:**
- Developer/Publisher (original): Konami (developer), Sega (arcade publisher), 1981 [Confirmed]
- Platforms (original): Arcade, then ported to ~40 platforms [Confirmed]
- Genre: Action arcade, lane-based dodge-and-ride
- Session length: 2–10 minutes per credit/game
- Play style: Active, single-player, high-score chase
- Age rating: ESRB E / PEGI 3 (original) [Confirmed]
- Monetization (clone): Ad-supported free-to-play web game; localStorage high-score persistence

---

## 2. Core Loops

- **30-second loop:** Tap/press a direction → character hops one grid cell → dodge or ride moving objects → land on goal slot OR die and restart the character from the start row.
- **Session loop:** Guide the character to all 5 goal slots to complete a level. Earn points for each hop forward, each goal reached, and time remaining. Fill all goals → level up, speed and complexity increase. Lose all lives → Game Over; try for a new high score.
- **Meta loop:** Chase a personal best score stored in localStorage. Each playthrough has the same start but each run is a fresh high-score attempt; players return to beat their own record.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics
1. **Discrete grid hopping:** The play field is a grid of rows and columns. Each input moves the character exactly one cell. No diagonal movement. [Confirmed]
2. **Road lanes (lower half):** Vehicles (cars, trucks, sports cars) move left or right at varying speeds in dedicated rows. Contact with any vehicle = instant death. [Confirmed]
3. **Safe zone (median strip):** One static row between road and river acts as a rest area. [Confirmed]
4. **River lanes (upper half):** Rows of water; character dies if standing on an uncovered cell. Floating platforms (logs, lily pads) move horizontally. The character is *carried* with any platform it stands on — the platform's velocity is added to the character's position each frame. [Confirmed]
5. **Platform types:**
   - **Log (short/long):** Solid surface, always visible, moves left or right. [Confirmed]
   - **Lily pad / Turtle group:** 2–3 linked platforms that periodically submerge (become unsafe for 1–2 s). Standing on submerged turtles = death. [Confirmed]
   - **Gator (optional, higher levels):** Looks like a log but the head section is lethal. [Estimated]
6. **Goal slots (top row):** 5 fixed home positions separated by bushes. Jumping into an occupied slot = death. Jumping into a bush side = death. [Confirmed]
7. **Timer:** Each life starts a countdown (default 30 s). Timer expires = death, full reset to start row. [Confirmed]
8. **Level completion:** Fill all 5 goal slots → award level-clear bonus → reset board with all goals empty, increase difficulty. [Confirmed]

### Core Verbs
- **Hop** (up/down/left/right) — the only player action
- **Ride** — passive; being carried by a platform (no input required)
- **Fill goal** — hop into an empty goal slot
- **Die** — hit vehicle, land in open water, timeout, off-screen, hit occupied goal

### Game Modes
| Mode | Description |
|---|---|
| Arcade (main) | Infinite levels, 3 lives, difficulty ramps, high-score chase |
| (No other modes in original) | — |

[Estimated: clone adds a Pause screen and restart option]

### Input Scheme
| Action | Keyboard | Touch |
|---|---|---|
| Hop up | Arrow Up / W | Swipe up OR D-pad Up button |
| Hop down | Arrow Down / S | Swipe down OR D-pad Down button |
| Hop left | Arrow Left / A | Swipe left OR D-pad Left button |
| Hop right | Arrow Right / D | Swipe right OR D-pad Right button |
| Pause | Escape / P | Pause button (HUD) |
| Mute | M | Mute button (HUD) |

**Orientation:** Portrait-preferred on mobile; landscape works on desktop. [Estimated for clone]
**Input feel:** Each input registers one discrete hop even if the key is held; no continuous movement. [Confirmed]

### Win / Lose / Fail Conditions
- **Win (level):** All 5 goal slots filled → level advance + bonus
- **Lose life:** Vehicle collision, open water, timeout, off-screen exit, occupied/wall goal
- **Game Over:** All lives exhausted
- **Failure handling:** On death, character respawns at start row, goals already filled remain filled, timer resets. On Game Over, show score + high score, offer restart.

### Difficulty Modes
No separate difficulty selection in original; difficulty scales automatically via level number. [Confirmed]

### Feedback Systems
- **Visual:** Squash/stretch hop animation; death flash/explosion sprite; goal-fill celebration particles; timer bar changes color when low
- **Audio:** Hop bloop, death crunch, goal chime, level-clear fanfare, splash (water death), time-warning beep
- **Screen shake:** On death only [Estimated]

---

## 4. Progression

### Level Progression
| Level Range | Changes |
|---|---|
| 1–5 | Baseline speeds; all platform types present; 30s timer |
| 6–10 | Vehicle speeds +20%; turtle submerge frequency increases; timer –2s per level |
| 11–15 | New fast vehicle lanes added; gator heads appear on logs; snake crossing median |
| 16+ | Max speed lanes; shortened timer; all hazard types active; pattern randomization |

[Estimated: Based on genre norms and documented original behavior]

### Unlock / Upgrade Trees
None in original (pure arcade). Clone has no upgrade system — progression is implicit difficulty. [Confirmed]

### Prestige / Rebirth
None. High score is the only persistent progression. [Confirmed]

### Gating
- Lives gate continued play
- Timer gates each individual attempt
- No paywall or soft-lock

---

## 5. Economy & RNG

### Currencies
| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| Score (points) | In-run only | Hops forward, goals, time bonus, level clear | Leaderboard position only — not spent |

No soft currency, hard currency, or premium economy. Pure arcade score system. [Confirmed]

### Scoring Formula
| Event | Points |
|---|---|
| Each hop forward (up) | 10 pts |
| Frog/character reaches home slot | 50 pts |
| Time bonus per 0.5s remaining | 10 pts |
| Bonus item (fly/candy on log) | 200 pts |
| Level clear (all 5 homes filled) | 1,000 pts |

[Confirmed from original arcade documentation]

### Cost-Scaling
No economy curves — score is purely additive with no spending. [Confirmed]

### RNG / Drop Rates
- Vehicle lane patterns: semi-random spacing within set speed tier [Estimated: gap size 1.5–4 cells, uniform random]
- Platform lengths: chosen from a set of 2–3 preset sizes per level [Estimated]
- Turtle submerge timing: 3–6s visible, 1.5–2.5s submerged, randomized per group [Estimated]
- Bonus item (fly/candy) appearance: ~15% chance per log per level spawn [Estimated]
- No gacha, no loot boxes

---

## 6. Content Inventory

### Levels / Stages
- Infinite levels (loop infinitely, increasing difficulty) [Confirmed]
- Board layout: ~13 rows total — 1 start row (safe), 5 road lanes, 1 median (safe), 5 river lanes, 1 goal row [Confirmed]
- Visual theme stays consistent (road + river) in original; clone may add seasonal skins [Estimated]

### Characters
- **Street Hopper** (player): small anthropomorphic toad/frog character, unique to clone brand
- **Lady Hopper** (bonus): rare character on a log, carry home for 200 pts [Confirmed mechanic, renamed]

### Vehicles (Road Obstacles)
| Type | Lanes | Speed | Width |
|---|---|---|---|
| Compact car | 1, 3 | Slow–Medium | 1 cell |
| Sports car | 2 | Fast | 1 cell |
| Truck | 4 | Slow | 2–3 cells |
| Bulldozer | 5 | Medium | 2 cells |

[Estimated counts; Confirmed vehicle type variety]

### River Platforms
| Type | Size | Speed | Special |
|---|---|---|---|
| Short log | 2 cells | Medium | — |
| Long log | 4 cells | Slow | — |
| Turtle group | 2–3 cells | Slow | Submerges periodically |
| Gator (lvl 6+) | 3 cells | Medium | Head cell = lethal |

### Goal Slots
- 5 home positions across top row
- Separated by 4 bush dividers
- Slot states: empty (safe to enter), occupied (lethal), goal-active (fills the slot)

### Collectibles
- Fly/candy icon: appears on logs, 200 pts, collected by hopping onto it

---

## 7. Theme, Narrative & Tone

**Setting:** A cheerful cartoon-style city road adjacent to a wide river. Cars zoom past, logs drift lazily downstream.

**Premise:** Street Hopper — a small toad with big ambitions — needs to cross a dangerous road and river to get home. No deeper story; the tension is entirely mechanical. [Confirmed: original has no narrative]

**Visual tone:** Bright, primary colors; slightly exaggerated cartoon style. Not cute-aggressive; more "classic toy" aesthetic. Friendly characters, no gore (vehicles bounce character off screen).

**Dialogue / Writing style:** Minimal — UI labels only ("LEVEL 3", "GAME OVER", "HIGH SCORE"). No dialogue. [Confirmed]

**Overall tone:** Tense-but-cheerful. Arcade classic feel. Family-friendly. The fun comes from near-misses and the satisfaction of finally making it across.

**IP:** Original clone — no Konami/Sega IP. Original character "Street Hopper" / brand.

---

## 8. Meta & Social Systems

**Daily/Weekly Missions:** None in original arcade. [Confirmed — clone omits these for MVP]

**Achievements:** None in original; clone stores personal best only. [Confirmed]

**Limited-time Events:** None. [Confirmed]

**Live-ops cadence:** N/A — this is a static arcade title with no live service. [Confirmed]

**Leaderboards:** No online leaderboard in original. Clone uses localStorage personal best only. [Confirmed for MVP]

**Multiplayer:** None. Single-player only. [Confirmed]

**Social features:** None in original. Share score button could be added. [Estimated — optional]

**Decoration / base building:** None. [Confirmed]

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|---|---|
| Start / Title screen | Show game logo, controls diagram, "Press any key / Tap to start" |
| Gameplay screen | Main play canvas with HUD overlay |
| Pause screen | Overlay with Resume / Restart / Mute options |
| Game Over screen | Shows final score, high score, "Play Again" button |
| (No login, no shop, no settings sub-screen beyond pause) | — |

### Settings / Options (within Pause screen)
- Sound mute toggle (SFX on/off)
- Resume game
- Restart game (resets score and lives)

### Gameplay HUD Elements
| Element | Position | Content |
|---|---|---|
| Score | Top-left | Current score ("SCORE: 1240") |
| High Score | Top-center | Best score ("BEST: 4500") |
| Level | Top-right | Current level number ("LVL 3") |
| Lives | Bottom-left | Heart/frog icons (3 max displayed) |
| Timer bar | Under HUD row | Horizontal bar draining left-to-right; turns red at <8s |
| Mute button | Bottom-right | Speaker icon toggle |

### Navigation Flow
```
Title Screen → [Any input] → Gameplay Screen
Gameplay Screen → [P/Esc] → Pause Screen → [Resume] → Gameplay Screen
                                          → [Restart] → Gameplay Screen (reset)
Gameplay Screen → [All lives lost] → Game Over Screen → [Play Again] → Gameplay Screen (reset)
Game Over Screen → [Play Again] → Title Screen or direct Gameplay reset
```

### Onboarding / Tutorial Steps
1. Title screen displays a simplified control diagram (arrow keys + WASD labeled; swipe gestures shown on mobile)
2. Level 1 starts at slowest speed — serves as the implicit tutorial
3. No explicit tutorial overlay in original; clone uses first-play hint text: "Avoid cars! Ride logs! Reach home!"
4. Hint text fades after 3 seconds
5. [Estimated — based on genre norms for arcade-style games]

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **2D top-down** (orthographic bird's-eye view) [Confirmed]
- Camera is **static** — entire board fits on screen at once [Confirmed]
- **Portrait-preferred** layout on mobile (board taller than wide); landscape acceptable on desktop

### Art Style
- **Flat cartoon / pixel-adjacent** — bold outlines, solid fills, minimal shading
- Color palette: bright greens (grass/median), grey asphalt, blue water, earthy log browns, red/yellow/blue vehicles
- Character: simple rounded toad silhouette with eyes; fits neatly in one grid cell
- Vehicles: top-down view, color-coded by type
- Platforms: wood-grain logs, green lily pads, darker turtle shells

### Animation Set
| Animation | Description |
|---|---|
| Idle | Subtle bob (scale oscillate 1.0→1.05) |
| Hop | Jump arc — squash on land, stretch in air — over 80ms |
| Death (vehicle) | Quick spin + fade |
| Death (water) | Ripple + sink |
| Death (timeout) | Flash white + fade |
| Goal fill | Bounce + sparkle particles |
| Turtle submerge | Gradual color darken + scale to 0.8 |

### VFX
- Particle burst on goal fill (gold stars)
- Ripple sprite on water death
- Screen flash (white, 150ms) on any death
- Timer bar color shifts: green → yellow → red

### Audio (WebAudio generated, no external files)
| Sound | Trigger | Character |
|---|---|---|
| Hop bloop | Every hop | Short sine wave pluck, ~80ms |
| Splash | Water death | Low frequency thud + noise burst |
| Squash | Vehicle death | Descending pitch crunch |
| Goal chime | Slot filled | Rising 3-note ding |
| Level clear fanfare | All slots filled | 5-note ascending melody |
| Time warning | <8s remaining | Repeating high beep every 1s |
| Game over jingle | All lives lost | Descending 4-note dirge |

### Overall "Juice"
- Discrete hop creates tactile rhythm; satisfying plunk audio per hop
- Near-miss moments (vehicle passes one cell away) create tension
- All 5 slots filled triggers a brief celebration (particles + fanfare) before reset — dopamine hit

---

## 11. Monetization

**Clone target:** Free-to-play web game; no IAP; minimal advertising.

### Ad Placements (Optional — for deployed version)
| Type | Placement | Frequency |
|---|---|---|
| Interstitial | On Game Over (every 3rd game over) | Low frequency |
| Banner | Below canvas (320×50) | Always shown if implemented |
| Rewarded video | "Continue?" option for 1 extra life (optional feature) | Player-initiated |

### IAP
None in base clone. If monetized: "Remove Ads" one-time IAP ~$0.99–$1.99. [Estimated]

### Loot Boxes / Gacha
None. [Confirmed — no RNG monetization]

### Subscriptions / Battle Pass
None. [Confirmed]

### Consent / ATT / CMP
- If ads are added: implement GDPR consent banner for EU users and iOS ATT prompt
- For the base HTML file (no ads, no tracking): no consent flow required
- [Estimated — applies only to deployed monetized version]

### Monetization Aggressiveness
Very low. Base clone is entirely free with no paywalls, no energy gates, no premium currency. Score is the only reward. [Confirmed design intent]

---

## 12. Retention Hooks

**Daily rewards:** None. [Confirmed — arcade genre has no login rewards]

**Login streaks:** None. [Confirmed]

**Offline / idle earnings:** None. This is an active arcade game with no idle mechanics. [Confirmed]

**Push notifications:** None for web HTML game. [Confirmed]

**Energy / lives:** Lives (3) act as a session-limiter but regenerate instantly on restart (no time gate). [Confirmed — arcade lives, not mobile energy mechanic]

**FOMO / urgency mechanics:** The 30-second per-life timer creates in-run urgency only. No external FOMO. [Confirmed]

**Core retention driver:** High-score competition with self. "One more run" loop is the primary hook — classic arcade design. [Confirmed]

---

## 13. Localization & Accessibility

### Languages
Original: Japanese/English only. Clone: English only for MVP. [Estimated — easy to extend]

### RTL Support
None required for English-only build. [Estimated]

### Text Scaling
Score/HUD text scales with canvas; no separate text-size setting in MVP. [Estimated]

### Colorblind Modes
MVP: None. Recommended addition: ensure vehicles are distinguishable by shape, not only color. [Estimated]

### Difficulty / Assist Options
- No explicit difficulty selector
- Level 1 is always the easiest (implicit assist for new players)
- No continue screen in original; clone may offer rewarded-video continue [Estimated]

### Age / Content Rating
ESRB: E (Everyone) / PEGI: 3 [Confirmed for original]
No violence (stylized cartoon only), no language, no adult content.
COPPA compliance: No data collection in localStorage-only build; no accounts; compliant by default. [Estimated]

### Regional Differences
None relevant to this clone. [Confirmed]

---

## 14. Technical Structure

### Engine / Framework
- Original: Custom Konami hardware [Confirmed]
- **Clone:** Vanilla JavaScript + HTML5 Canvas API — no framework, no build step [Confirmed for this deliverable]
- Single self-contained `.html` file (inline CSS + JS)

### Platforms
- Any modern browser (Chrome, Firefox, Safari, Edge) [Confirmed]
- Desktop + mobile (responsive canvas) [Confirmed]
- Offline capable (no network requests) [Confirmed]

### Save System
- localStorage key: `streetHopperBest` — stores numeric high score
- No cloud save, no account system
- Cross-device sync: N/A [Confirmed]

### Multiplayer / Netcode
None. Single-player only. No server. [Confirmed]

### Anti-Cheat / Server Authority
N/A — single-player, client-only, no competitive online component. [Confirmed]

### Backend Services
None. Fully client-side. [Confirmed]

### SDKs / Analytics
None in base build. [Confirmed]

### App Size
~40–80 KB (HTML + inline JS/CSS, no external assets) [Estimated]

### Performance Notes
- Target 60 fps on mid-range mobile
- Canvas 2D API (no WebGL needed)
- All sprites drawn procedurally (no image files)
- WebAudio API for sound generation

---

## 15. Pacing & Difficulty

### Early Game (Levels 1–3)
- Slow vehicles, wide gaps
- Logs long and plentiful, turtles always visible (no submerge)
- Timer generous (30s)
- Players learn the hop mechanic, discover vehicle timing
- **Milestone:** First successful goal fill — "aha" moment

### Mid Game (Levels 4–8)
- Vehicle speeds increase; gaps narrow
- Shorter logs appear; turtles begin submerging
- Occasional snake/hazard in median row
- Timer reduced to ~22s
- Players must plan 2–3 moves ahead
- **Milestone:** First level-clear (all 5 goals) — significant dopamine reward

### Late Game (Levels 9+)
- Fast multi-lane chaos; gators on river
- Very short platforms; overlapping timing windows
- Timer as low as 15s
- Requires memorization of platform rhythms
- **Churn point:** Most casual players exit here; hardcore players continue for score

### Difficulty Curve Formula (Estimated)
```
vehicleSpeed[lane] = baseSpeed[lane] * (1 + 0.12 * (level - 1))
platformSpeed = basePlatformSpeed * (1 + 0.08 * (level - 1))
timer = max(15, 30 - (level - 1) * 1.5)
turtleSubmergeChance = min(0.9, 0.3 + level * 0.06)
```

### Key Churn Points [Estimated from genre norms]
1. Level 1 first death — frustration from new players
2. Level 4–5 — turtle submerge catches mid-skill players
3. Level 8+ — speed ramp feels punishing without practice

---

## 16. Clone Build Plan

### MVP Feature Set
- [x] Grid-based hop movement (4 directions, discrete)
- [x] Road section: 5 lanes, vehicles moving left/right at varying speeds
- [x] Median safe zone
- [x] River section: 5 lanes, logs and turtles moving left/right
- [x] Player carried by platform (velocity inheritance)
- [x] Water death when not on platform
- [x] Turtle submerge mechanic
- [x] Goal row: 5 slots, fill all to advance level
- [x] Lives system (3 lives)
- [x] Per-life countdown timer (30s, displayed as drain bar)
- [x] Scoring: hops + goals + time bonus + level clear
- [x] localStorage high score persistence
- [x] Level difficulty scaling (speed, timer, turtle frequency)
- [x] Start screen with controls
- [x] Game Over screen with score
- [x] Pause/Resume
- [x] Keyboard controls (arrows + WASD)
- [x] Touch controls (swipe + on-screen D-pad)
- [x] Responsive canvas (fits any screen)
- [x] WebAudio SFX (no external files)
- [x] Mute toggle

### Full Feature Set (Post-MVP)
- [ ] Online leaderboard
- [ ] Additional vehicle/platform types (alligators, snakes)
- [ ] Bonus collectibles (fly/candy on logs)
- [ ] Lady Hopper bonus character
- [ ] Seasonal visual themes
- [ ] Rewarded-video "continue" option
- [ ] Share score button

### Phased Build Roadmap

**Phase 1 — Core Engine (Day 1)**
- HTML canvas setup, grid constants, game loop (requestAnimationFrame)
- Player entity: position (col, row), hop input, boundary clamp
- Render: draw grid cells, player sprite (procedural)

**Phase 2 — Road Section (Day 1–2)**
- Vehicle entity: lane, direction, speed, width
- Vehicle spawn/despawn at screen edges
- Collision detection: player cell overlaps vehicle cell(s) → death
- Multiple lanes with different speeds

**Phase 3 — River Section (Day 2)**
- Platform entity: type (log/turtle), direction, speed, cell list
- Platform carry: add platform velocity to player position each frame
- Water death: player not on any platform cell in river rows → death
- Turtle submerge: timer-based state toggle (visible/submerged)

**Phase 4 — Goal System + Level Progression (Day 2–3)**
- Goal slots: array of 5 booleans; detect hop into slot
- Level clear: all 5 true → award bonus → reset board, increment level
- Difficulty scaling: apply level multipliers to speed/timer values

**Phase 5 — HUD + Game States (Day 3)**
- Game state machine: START → PLAYING → PAUSED → DEAD → GAME_OVER
- Score display, lives display, timer bar, level display
- High score read/write localStorage
- Start screen, pause overlay, game over overlay

**Phase 6 — Controls + Touch (Day 3–4)**
- Keyboard handler (keydown debounce)
- Touch swipe detection (touchstart/touchend delta)
- On-screen D-pad: 4 buttons, touchstart/mousedown

**Phase 7 — Audio + Polish (Day 4)**
- WebAudio context: oscillator-based SFX for each event
- Mute toggle
- Hop animation (squash/stretch), death animation, goal fill particles
- Screen flash on death
- Timer bar color change

**Phase 8 — Self-Review + QA (Day 4–5)**
- Test car collision accuracy
- Test log carry math (player position = player + platform delta per frame)
- Test water death edge cases (between platforms)
- Test goal slot fill detection
- Test level reset / goal state clear
- Test resize handler
- Test touch on mobile
- Test restart resets all state cleanly
- Verify </html> termination, no console errors

### Recommended Tech Stack (Clone)
- Vanilla HTML5 / JavaScript (ES2020)
- Canvas 2D API for rendering
- Web Audio API for SFX
- localStorage for persistence
- No external libraries, no build tools, no CDN

### Required Asset List
All assets are **procedurally drawn** in Canvas 2D — no image files needed.

| Asset | Implementation |
|---|---|
| Player character | Rounded rectangle + circle head + eyes, green fill |
| Vehicles | Colored rectangles with wheel dots, color by type |
| Logs | Brown rectangles with grain lines |
| Turtles | Dark green rounded rects with shell pattern |
| Goal slots | Dark green arch shapes, white lily pad interior |
| Background rows | Color-coded fills (grey road, blue water, green safe) |
| Particles | Small yellow/gold circles on goal fill |
| UI elements | fillText + fillRect — no images |

### Hardest Parts / Risks
1. **Platform carry math:** Player must move with the platform every frame, not just on hop. Off-by-one or frame-rate dependency causes "sliding off" bugs. Solution: track player platform reference; update player X by platform delta each tick.
2. **Water death edge case:** Player hopping between two adjacent platforms must not trigger water death on the frame of the hop. Solution: evaluate platform collision AFTER applying position, not before.
3. **Responsive canvas + grid alignment:** Canvas resizes must recalculate cell size and maintain integer grid snapping. Solution: recalculate CELL_SIZE on every resize event.
4. **Touch D-pad on mobile:** Buttons must be large enough for fat-finger accuracy and must not conflict with swipe detection. Solution: D-pad in lower 20% of screen; swipe only in upper 80%.
5. **Collision precision:** Vehicle width > 1 cell means collision must check all cells occupied by the vehicle, not just its origin. Solution: iterate vehicle cells, compare to player cell each frame.

---

## 17. Open Questions

1. **Turtle submerge warning:** Should there be a visual warning (flicker) before turtles submerge? Original arcade does flash. Recommended: yes — 0.5s flicker before submerge. [Needs playtesting to tune duration]
2. **Level cap:** Does difficulty continue scaling past level 20, or should speed be capped to keep the game theoretically winnable? Recommendation: cap vehicle speed at 2.5x base, platform speed at 2x base, around level 15. [Needs playtesting]
3. **Hop input buffering:** Should inputs during a hop animation be queued (buffer) or discarded? Original discards; buffering feels more responsive. Recommend 1-hop buffer. [Playtesting to decide]
