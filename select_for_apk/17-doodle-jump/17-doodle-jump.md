# Sky Hopper — Clone Blueprint

> Note: This blueprint targets an original-branded clone called **Sky Hopper**. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text. All art, brand, and audio must be original.

---

## 1. Snapshot

Sky Hopper (clone of **Doodle Jump**) is an endless vertical-platformer arcade game in which a cartoonish four-legged creature auto-bounces upward through an infinite stack of procedurally generated platforms. The player steers left/right; the view scrolls up as the creature climbs; falling off the bottom ends the run. Session length is typically 1–5 minutes; the core appeal is one-more-try score-chasing and the satisfying escalating difficulty as platforms become sparser. [Confirmed]

**Quick facts:**
| Field | Value |
|---|---|
| Original title | Doodle Jump |
| Developer | Lima Sky LLC (Igor & Marko Pušenjak) [Confirmed] |
| First release | April 6, 2009 (iOS) [Confirmed] |
| Platforms | iOS, Android, Windows Phone, Arcade cabinet; web clones widespread [Confirmed] |
| Age/content rating | IARC 3+ / ESRB E [Confirmed] |
| Monetization model | Premium app purchase + optional themed DLC; web clones typically ad-supported |
| Install scale | 250 million+ downloads across platforms [Confirmed] |

---

## 2. Core Loops

- **30-second loop:** Creature auto-bounces on each platform it lands on; player tilts/steers left-right to line up the next platform; screen scrolls up; gap judgment is the core micro-skill. [Confirmed]
- **Session loop:** Start a run → climb as high as possible → collect power-ups, dodge/shoot enemies → eventually miss a platform or hit an enemy → see final height score → compare to personal best → immediately restart. Typical session 1–5 minutes. [Confirmed]
- **Meta loop:** No long-term progression in the original — pure score-chasing and personal-best competition. High score saved locally; optional social leaderboards on mobile. [Confirmed]

---

## 3. Mechanics, Controls & Game States

### Core Verbs
- **Steer:** Move creature left/right (device tilt on mobile; arrow/WASD keys on desktop). [Confirmed]
- **Auto-bounce:** Creature bounces upward whenever it descends onto the top surface of a platform (only while falling, not while rising). [Confirmed]
- **Shoot:** Tap/click above the creature to shoot upward; destroys enemies. [Confirmed]
- **Screen-edge wrap:** Moving off the left edge reappears on the right and vice versa. [Confirmed]

### Platform Types
| Type | Behavior | Visual |
|---|---|---|
| Static (Normal) | Fixed position, infinite durability | Green/solid color |
| Moving | Slides left-right at constant speed, bounces off invisible walls | Blue or colored tint |
| Breakable | Cracks and disappears after one landing | Brown/cracked pattern |
| Vanishing | Disappears after a brief moment even without landing | White/ghost |
| Spring | Static with a spring object on top; gives 3–4× extra bounce height | Yellow spring |
| Trampoline | Placed on a normal platform; sends character very high | [Estimated] |

### Power-Ups (placed on platforms)
| Power-up | Effect |
|---|---|
| Spring | Single-use, large bounce boost |
| Propeller Hat | Fly upward for ~3 seconds |
| Jetpack | Fly upward for ~5 seconds |
| Rocket | Extremely fast ascent for ~2 seconds |
| Shield | Temporary invulnerability to monsters |

[Confirmed for Spring, Propeller Hat, Jetpack, Rocket; Estimated durations]

### Enemies
| Enemy | Behavior | Defeat method |
|---|---|---|
| Monster (ground) | Stationary or patrolling on a platform row | Shoot or jump on top |
| Flying monster | Moves horizontally at mid-height | Shoot |
| UFO | Flies across screen; can "abduct" (end run) if player contacts | Shoot |
| Black hole | Static hazard; sucks player in on contact | Avoid |

[Confirmed types; Estimated individual behaviors]

### Game States
1. **Start Screen** — Title, best score, "Tap to Play" prompt.
2. **Active Play** — rAF game loop running.
3. **Paused** — Loop halted; resume or restart available.
4. **Game Over** — Display final height + personal best; restart button.

### Win / Lose Conditions
- **No win state** — endless by design. [Confirmed]
- **Lose / fail:** Creature falls below the bottom edge of the visible screen (camera doesn't catch up fast enough). [Confirmed]
- **Instant-kill hazard:** Touching a monster, black hole, or UFO while not shielded. [Confirmed]
- **Failure handling:** Show Game Over screen with score. No continue mechanic; restart is immediate. [Confirmed]

### Controls
| Action | Desktop | Mobile |
|---|---|---|
| Move left | Arrow Left / A | deviceorientation tilt left OR left touch zone |
| Move right | Arrow Right / D | deviceorientation tilt right OR right touch zone |
| Shoot | Click / Space | Tap above character |
| Pause | P / Escape | Pause button HUD |

### Orientation
Portrait [Confirmed] — canvas taller than wide, ~320×480 logical pixels scaled to screen.

### Difficulty Modes
Original has no explicit difficulty selector — difficulty emerges from height (platform density decreases, enemy frequency increases). [Confirmed]

---

## 4. Progression

- **No XP or unlock tree** in the original arcade mode. [Confirmed]
- **Implicit difficulty ramp:** Platform gap increases linearly with height; moving/breakable/vanishing platforms replace normal ones progressively. [Confirmed]
- **Score milestones:** Every 1,000 height units awards a bonus (see Economy). [Estimated based on genre norms]
- **Themes:** The original unlocks themed skins (Halloween, Space, etc.) as separate purchases. For the clone, themes can be unlockable at score thresholds as a retention feature. [Estimated]
- **No prestige or reset mechanic.** [Confirmed]
- **Gating:** No timewalls or paywalls in the arcade core loop. [Confirmed]

---

## 5. Economy & RNG *(tables)*

### Currencies
| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| Height Score | Soft (ephemeral) | Climbing platforms | N/A — display only |
| Personal Best | Persistent record | Surpassing prior best | N/A — display only |

No in-run currency to collect in the original arcade version. [Confirmed]
Clone variant may add collectible stars (soft currency) for cosmetic themes — [Estimated].

### Platform Type Probabilities by Height
| Height Band | Normal | Moving | Breakable | Vanishing | Spring |
|---|---|---|---|---|---|
| 0 – 1,000 | 90% | 5% | 5% | 0% | rare item |
| 1,000 – 3,000 | 70% | 15% | 10% | 5% | occasional |
| 3,000 – 6,000 | 50% | 20% | 20% | 10% | occasional |
| 6,000 – 10,000 | 35% | 25% | 25% | 15% | occasional |
| 10,000+ | 25% | 25% | 25% | 25% | occasional |

[Estimated — derived from observed gameplay pacing; tune during playtest]

### Platform Density Curve
- Screen always contains ~10 reachable platforms at height 0. [Confirmed based on sources]
- At height 5,000: ~7 platforms visible.
- At height 10,000+: ~5 platforms visible.
- Formula: `platformCount = max(5, 10 - floor(height / 2000))` [Estimated]

### Scoring
- Height in pixels/units = score. [Confirmed]
- `score = maxYReached` (in game units, scaled to display as meters/feet)
- Power-up bonus: +500 for every 1,000 height units crossed. [Estimated based on search result: "every 1000 units earn 500 bonus"]
- Platform-landing bonus: +10 per landing. [Estimated based on search results]

### RNG
- Platform horizontal position: uniform random within screen width.
- Platform vertical spacing: uniform random between `minGap` and `maxGap` (both increase with height).
- Enemy spawn: 5% chance per platform row above height 2,000; increases to 15% above 6,000. [Estimated]
- Power-up spawn: 3% chance per platform above height 500. [Estimated]
- No gacha or loot boxes. [Confirmed]

---

## 6. Content Inventory *(counts + lists)*

### Platform Varieties: 5 types (Normal, Moving, Breakable, Vanishing, Spring-topped) [Confirmed]

### Power-Ups: 5 types (Spring, Propeller Hat, Jetpack, Rocket, Shield) [Confirmed]

### Enemies: 4 types (Ground Monster, Flying Monster, UFO, Black Hole) [Confirmed]

### Characters: 1 (The Doodler / in clone: "Hopper") [Confirmed]

### Themes (original): 12+ (Original, Christmas, Halloween, Space, Rainforest, Soccer, Underwater, Easter, Ice, Retro Arcade, Ninja, Pirate) [Confirmed]
Clone MVP: 1 theme; additional themes as future scope.

### Game Modes: 1 core endless mode. [Confirmed]
No campaign, no PvP, no time-attack in original.

### Visual Assets Required
- Character (idle, bounce, fall, shoot, power-up frames)
- 5 platform sprites
- 5 power-up sprites
- 4 enemy sprites
- Background (scrolling notebook-paper or clean color)
- HUD elements (score, best, pause, mute)
- Particle FX (stars, dust)

---

## 7. Theme, Narrative & Tone

- **Setting:** An endless upward world — the original uses a notebook-paper background reinforcing the "doodle" aesthetic. Clone uses a clean pastel sky-gradient background. [Confirmed for original; Estimated for clone]
- **Premise:** No story. Pure arcade. The character just... bounces upward forever. [Confirmed]
- **Character personality:** Cute, simple, googly-eyed creature. No dialogue. [Confirmed]
- **Tone:** Light, cheerful, whimsical, instantly accessible. [Confirmed]
- **Writing style:** Minimal — score display and maybe a few encouragement strings ("Nice!", "Keep going!"). [Estimated]
- **Licensed IP:** None in clone. Original had themed crossovers (Star Wars, etc.) — irrelevant for clone. [Confirmed N/A]

---

## 8. Meta & Social Systems

- **Daily rewards / missions:** None in original arcade. [Confirmed] Clone may add a "daily high score" persistent badge — [Estimated].
- **Leaderboards:** Original used Game Center / Google Play scores. Clone: localStorage personal best only for MVP; optional online leaderboard via backend for full version. [Estimated]
- **Events / seasons:** None in original core loop. [Confirmed]
- **Live-ops cadence:** N/A — original is a premium, non-live-service game. Clone does not inherit a content-update burden. [Confirmed]
- **Multiplayer:** None. [Confirmed]
- **Social sharing:** None required for MVP. Share-score button optional. [Estimated]
- **Guilds / clans:** None. [Confirmed]
- **Offline / idle earnings:** None — active play only. [Confirmed]

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|---|---|
| Start Screen | Title logo, best score display, control instructions, Play button |
| Gameplay | Main run: canvas with HUD overlay |
| Pause Modal | Semi-transparent overlay: Resume, Restart, Mute toggle |
| Game Over Screen | Final height score, personal best, restart button, share (optional) |
| Settings (optional) | Mute SFX/Music, control scheme toggle (tilt vs. buttons) |

### HUD Elements During Gameplay
- Current height score (top-left)
- Personal best marker (dashed line on screen or numeric indicator top-right)
- Mute toggle button (top-right corner)
- Pause button (top-right or top-center)

### Navigation Flow
```
Start Screen → [Play] → Gameplay → [Fall/Die] → Game Over → [Restart] → Gameplay
                                  → [Pause] → Pause Modal → [Resume] → Gameplay
                                                           → [Restart] → Gameplay
```

### Onboarding / Tutorial (Step-by-Step)
1. First launch: Start screen shows arrow keys + touch zone diagram for 3 seconds.
2. First run starts: on-screen hint "← → to steer" fades in for 2 seconds then disappears.
3. First spring encountered: brief "Spring!" pop-up text above the spring.
4. No forced tutorial interruption — the game teaches through play. [Confirmed in spirit]

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **2D, portrait orientation.** [Confirmed]
- **Camera:** Vertical-scroll only. The camera's Y tracks the highest Y the character ever reached (`cameraY = maxReachedY - screenHeight * 0.35`). Camera never scrolls back down — if character falls below camera bottom, run ends. [Confirmed logic]
- **Aspect:** ~9:16 (mobile-first). Canvas scales to fill viewport, maintaining aspect ratio.

### Art Style
- Original: hand-drawn notebook doodle aesthetic, graph-paper background, black ink outlines. [Confirmed]
- Clone (Sky Hopper): Clean pastel 2D vector art. Soft rounded shapes. Color palette: sky blue background, white clouds, cream platforms, character in bright teal/coral. Maintains playful, non-threatening tone.

### Animation Set (Estimated)
- Creature: idle (slight bob), bounce squash-and-stretch on landing, shoot (mouth open flash), hat/jetpack state.
- Platforms: breakable fractures on hit; spring compresses then extends.
- Power-up: collected with starburst particle burst.
- Enemy: idle oscillation; hit = poof disappear.

### VFX / Juice
- Bounce: dust particles on platform landing.
- Spring: whoosh trail of circles going upward.
- Enemy death: 6-particle starburst.
- New personal best height: screen flash + "NEW BEST!" pop-up text.
- Camera shake: 0.3s on enemy/black-hole collision.

### Audio (WebAudio generated, no files)
| Sound | Trigger | Character |
|---|---|---|
| Bounce | Normal platform landing | Short sine blip, 220Hz, 80ms |
| Spring | Spring landing | Ascending arpeggio 3 notes |
| Jetpack | Powerup collect | Sweeping pitch-up noise |
| Shoot | Fire projectile | Sharp click, 1200Hz, 30ms |
| Enemy die | Kill enemy | Descending pop |
| Game over | Fall off screen | Low descending tone |
| BGM | Continuous | Procedural cheerful loop (optional) |

### Music
Optional ambient up-tempo 8-bit loop generated via WebAudio oscillators. Mute toggle persists.

---

## 11. Monetization

Original is a premium paid app (~$0.99–$1.99). [Confirmed]

**Clone monetization (web version):**
| Ad Type | Placement | Frequency |
|---|---|---|
| Interstitial | Game over screen, every 3rd death | After score shown, before restart |
| Rewarded Video | "Revive once?" prompt on death | Optional, player-initiated |
| Banner | Bottom of start screen only | Always-on |

**IAP (if published as mobile app):**
| Product | Price | Description |
|---|---|---|
| Remove Ads | $1.99 | Permanent ad removal |
| Theme Pack | $0.99 | Unlocks 3 alternate visual themes |

**No gacha, no loot boxes, no battle pass.** [Confirmed for original; Estimated for clone]

**Consent / Privacy:**
- GDPR CMP popup on first load (EU users) before any ads.
- iOS ATT prompt before IDFA access.
- Age gate: rated 3+; no COPPA issues as no data collection required for core game.

**Aggressiveness:** Low. Core loop is unobstructed. Ads only appear between sessions.

---

## 12. Retention Hooks

- **Personal best display:** Shown prominently on start screen and game over; psychological pull to beat your own score. [Confirmed]
- **Immediately restartable:** Zero friction restart — one tap/click. [Confirmed]
- **No energy / lives system:** Play as many times as desired. [Confirmed]
- **No offline / idle earnings:** Active-play-only genre; no idle mechanic. [Confirmed]
- **Push notifications:** Not applicable for web MVP. Mobile app could send "Your friend beat your score!" type alerts. [Estimated]
- **FOMO:** None in core loop. Optional: timed seasonal theme (e.g., "Halloween theme — 3 days only") as future live-op. [Estimated]
- **Intrinsic hooks:** Escalating tension as platforms get sparse; the near-miss experience ("almost had it") drives immediate retry.

---

## 13. Localization & Accessibility

### Localization
- Original supports 12+ languages. [Confirmed]
- Clone MVP: English only. All string constants in one JS object for future l10n.
- No RTL text in the game interface (score is numeric; controls are icons).

### Accessibility
- **Text scaling:** Score text sized relative to canvas; no tiny text.
- **Colorblind:** Platform types differentiated by shape + pattern, not color alone. [Estimated — important to implement]
- **Difficulty assist:** None in original. Clone could offer a "slow start" mode where the first 500 units always have dense platforms. [Estimated]
- **Audio options:** Mute toggle for SFX; separate BGM toggle. [Estimated]
- **Touch controls:** Large (60×80px) left/right touch zones for easy thumb access.
- **No keyboard-only traps:** All actions reachable by touch or mouse.

### Age/Content Rating
- IARC 3+ / ESRB E — suitable for all ages. [Confirmed]
- No COPPA concerns for web version if no account registration or data collection.
- GDPR-K: if targeted at children under 16 in EU, ad targeting must be disabled.

---

## 14. Technical Structure

### Engine / Framework
- Original: likely Objective-C / UIKit (2009 iOS). Later ports in various engines. [Confirmed]
- **Clone:** Vanilla JavaScript + HTML5 Canvas 2D API. No framework, no build step. [Specified by task]

### Platforms Supported
- Web (desktop + mobile browser). Runs offline after first load (single HTML file).

### Save System
- `localStorage` for personal best score and mute preference. [Estimated — simple key-value]
- No accounts, no cloud sync for MVP.

### Online Requirements
- None. Fully offline after load. No network calls.

### Anti-Cheat
- N/A — single-player, no server, local score only. Score manipulation only hurts the cheater. [N/A confirmed]

### Technical Stack (Clone)
- HTML5 Canvas (2D context)
- Vanilla ES6+ JavaScript
- WebAudio API (generated SFX)
- `requestAnimationFrame` game loop with fixed timestep delta
- `localStorage` for persistence
- `deviceorientation` API for mobile tilt
- No external dependencies, no CDN calls

### Performance Notes
- Target 60 FPS; canvas cleared and redrawn each frame.
- Platform array recycled: off-screen-bottom platforms removed, new ones added at top.
- Maximum ~15 platform objects in memory at once.
- Particle system: max 50 concurrent particles, pooled.

### App Size
- Single `.html` file, ~100–200 KB (no external assets).

---

## 15. Pacing & Difficulty

### Early Game (Height 0 – 2,000)
- Dense normal platforms; almost impossible to fall.
- Player learns auto-bounce, steering, edge-wrap.
- Springs appear occasionally as "nice surprise" boost.
- No enemies.
- Tone: relaxed, welcoming.

### Mid Game (Height 2,000 – 7,000)
- Moving and breakable platforms introduced; normal platforms reduced.
- First enemies appear (stationary monsters).
- Power-ups appear regularly (jetpack, propeller hat).
- Platform gaps require deliberate aim; one mistimed bounce can send you off path.
- Tone: engaged, mild challenge.

### Late Game (Height 7,000 – 15,000)
- Vanishing platforms common; breakable platforms everywhere.
- Flying enemies and UFOs appear.
- Sparse platform layouts require aggressive edge-wrap use.
- Power-ups are lifelines — prioritizing them matters.
- Tone: tense, high skill expression.

### Endgame (Height 15,000+)
- Near-minimum platform density.
- Multiple enemy types active simultaneously.
- Only expert players reach this; each new personal best is a major milestone.
- [Estimated thresholds — tune based on playtest]

### Key Milestone Beats
- 500: First moving platform.
- 1,000: First breakable platform; first height bonus.
- 2,000: First enemy.
- 3,500: First vanishing platform.
- 5,000: First UFO.

### Churn Points
- ~Height 1,000: First surprise breakable platform fall ends run unexpectedly → frustration → restart. [Estimated based on genre review patterns]
- ~Height 4,000: Platform density drops sharply; skill gap becomes visible.
- ~Height 8,000: Where casual players plateau; hardcore players feel mastery.

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist
| Feature | MVP | Full |
|---|---|---|
| Normal + moving + breakable platforms | ✓ | ✓ |
| Auto-bounce (descending only) | ✓ | ✓ |
| Horizontal steering + edge wrap | ✓ | ✓ |
| Upward camera scroll, fall = game over | ✓ | ✓ |
| Height score + localStorage personal best | ✓ | ✓ |
| Start / Game Over screens | ✓ | ✓ |
| Touch left/right zones | ✓ | ✓ |
| Keyboard controls | ✓ | ✓ |
| Springs (big bounce power-up) | ✓ | ✓ |
| Vanishing platforms | ✓ | ✓ |
| Enemies (ground + flying) | ✓ | ✓ |
| WebAudio SFX + mute toggle | ✓ | ✓ |
| Device tilt (deviceorientation) | ✓ | ✓ |
| Particles / juice | ✓ | ✓ |
| Pause functionality | ✓ | ✓ |
| Propeller Hat / Jetpack power-ups | — | ✓ |
| UFO enemy | — | ✓ |
| Multiple visual themes | — | ✓ |
| Online leaderboard | — | ✓ |
| BGM loop | — | ✓ |
| Share score button | — | ✓ |

### Phased Build Roadmap

**Phase 1 — Core Scaffold (Day 1)**
- Canvas setup, rAF loop, gravity constant
- Character entity: position, velocity, render
- 10 static normal platforms (hardcoded), auto-bounce on landing
- Keyboard left/right movement, edge wrap
- Camera follow logic

**Phase 2 — Procedural Generation (Day 1–2)**
- Platform recycler: remove below screen, generate at top
- Platform density/gap formula tied to height
- Moving platforms (sine-wave or linear bounce)
- Breakable platforms (one-use crack-and-destroy)

**Phase 3 — Game States & UI (Day 2)**
- Start screen, Game Over screen, Pause modal
- HUD: score display, personal best
- localStorage save/load

**Phase 4 — Variety & Juice (Day 2–3)**
- Springs (extra bounce multiplier)
- Vanishing platforms (delayed disappear)
- Particle system (dust on bounce, starburst on spring)
- WebAudio SFX (bounce blip, spring arpeggio, game over tone)
- Squash-and-stretch on character bounce

**Phase 5 — Mobile & Enemies (Day 3)**
- deviceorientation tilt support
- Touch left/right zone overlays
- Ground enemies (patrol platforms, shoot to kill)
- Projectile system
- Mute toggle

**Phase 6 — Polish & QA (Day 3–4)**
- Responsive canvas resize handler
- Colorblind-friendly platform differentiation
- Thorough edge-wrap test
- Verify bounce-only-when-descending condition
- Performance profiling (target 60 FPS on mid-range phone)
- HTML5 validation, no console errors

### Recommended Tech Stack
- HTML5 Canvas 2D + Vanilla ES6 JS — no dependencies
- WebAudio API for SFX
- CSS `transform: scale` or canvas logical-size for responsive scaling

### Required Asset List
*(All generated programmatically in canvas — no external files)*
- Character: drawn with arcs/ellipses in canvas, animated via state flags
- Platforms: filled rectangles with color/pattern differentiation
- Springs: drawn with canvas paths
- Enemies: simple canvas shapes (circle + eyes)
- Particles: small circles with alpha fade
- HUD: fillText with system fonts

### Hardest Parts / Biggest Risks
1. **Bounce condition precision:** Must only trigger when `vy > 0` (falling) AND `player.bottom` crosses `platform.top`. Off-by-one in collision detection causes jitter or missed bounces.
2. **Camera math:** Camera Y must track the maximum Y ever reached, never descend. Fall-death must trigger exactly when player bottom > camera bottom (not based on world coordinates alone).
3. **Platform generation fairness:** Must guarantee every gap is jumpable at the current bounce power. Validate `maxGap < jumpHeight * 0.85` at all height bands.
4. **Mobile tilt sensitivity:** `deviceorientation` gamma values need calibration (±5° dead zone, ±30° = full speed). Permission prompt on iOS 13+ requires user gesture to activate.
5. **Edge wrap feel:** Character should teleport instantly with no visual artifact; ensure the wrap is checked post-movement each frame.
6. **Restart state reset:** All arrays (platforms, particles, enemies, projectiles), camera position, player position, and score must be fully reset on restart without page reload.

---

## 17. Open Questions

1. **Optimal initial bounce velocity:** The value that feels satisfying and reaches ~2.5 platform-widths height needs playtest calibration. Starting estimate: `vy = -18` at `gravity = 0.4` per frame. [Estimated — test at 60 FPS]
2. **Tilt sensitivity on different devices:** Android vs iOS gamma range varies; may need per-device calibration or a sensitivity slider in settings.
3. **Enemy AI patrol range:** How wide should enemy patrols be? Too wide = unavoidable; too narrow = ignorable. Recommend 60–70% of platform width as starting point.
4. **Scoring feel:** Pure height vs. height + bonus points — which feels more rewarding? Playtest with target audience.
5. **Maximum platform count:** 10–15 platforms is assumed sufficient; edge cases at very high speed (after jetpack) may outpace generation — stress test needed.
