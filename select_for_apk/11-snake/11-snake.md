# Snake — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Snake is a single-player arcade game in which the player steers a continuously moving snake around a bounded grid, eating food to grow longer and accumulate points, while avoiding collision with walls or the snake's own body. The appeal is pure, skill-based tension: the longer you survive, the harder evasion becomes. It is one of the most influential mobile games ever made, originally created by Taneli Armanto for the Nokia 6110 in 1997. [Confirmed]

**Quick facts:**
| Field | Detail |
|---|---|
| Original developer | Taneli Armanto / Nokia (1997) [Confirmed] |
| Platforms (original) | Nokia feature phones; countless web/app clones since |
| Genre | Arcade / reflex |
| Session length | 1–5 minutes |
| Play style | Active, single-player |
| Age/content rating | IARC Everyone (no violence, no COPPA concerns for pure gameplay) [Estimated] |
| Monetization model (clone) | None (arcade freeware); optional ad/IAP layer described in §11 |

---

## 2. Core Loops

- **30-second loop:** Steer the snake one cell at a time with directional input; reach and eat the food pellet; avoid hitting walls or your own tail; repeat at progressively higher speed.
- **Session loop:** Start from length-3 snake; eat as many pellets as possible before dying; chase a personal high score; see game-over screen; restart immediately.
- **Meta loop:** Beat personal best stored in localStorage; implied social competition via shared scores. No persistent unlock tree — replay value comes from the skill curve alone. [Confirmed — classic arcade model]

---

## 3. Mechanics, Controls & Game States

### Core Mechanics
1. **Grid movement** — Snake occupies discrete cells on a fixed grid (20 × 15 recommended for mobile, or 25 × 20 for landscape desktop). Moves exactly one cell per tick. [Confirmed]
2. **Continuous motion** — Snake always moves forward; player only changes direction.
3. **Growth** — Eating food adds one segment to the tail; score increments by 10. [Estimated: ×10 multiplier for polish]
4. **Wall death** — Snake head exiting the grid boundary = instant game over. [Confirmed — "walls = death" classic variant]
5. **Self-collision death** — Snake head entering any occupied body cell = instant game over. [Confirmed]
6. **Food spawn** — One food pellet exists at all times; it spawns at a random empty cell after being eaten. [Confirmed]
7. **Speed increase** — Game tick interval decreases as snake length grows (see §15 for curve). [Confirmed]
8. **180° reversal guard** — Input that would reverse direction on the same axis is silently discarded. [Confirmed]

### Core Verbs
- **Steer** (up / down / left / right) — the only player action.

### Game Modes
| Mode | Description |
|---|---|
| Classic Arcade | Single endless game; walls = death; speed scales with length |

(No additional modes in MVP; see §16 for optional extras.)

### Input Scheme
| Input | Action |
|---|---|
| Arrow keys | Steer (desktop) |
| WASD | Steer (desktop) |
| On-screen D-pad buttons | Steer (mobile/touch) |
| Swipe on canvas | Steer (mobile/touch) |
| P / Escape | Pause / Resume |
| Enter / Space | Start game / Restart after game over |

**Orientation:** Portrait-primary on mobile; landscape supported on desktop.

### Win / Lose / Fail States
- **Win:** None — game is endless; theoretical max score is (grid cells − 3) × 10 = 2,970 on a 20×15 grid. [Estimated]
- **Lose / Game Over:** Head hits wall or own body → freeze, show game-over overlay with final score + best score + restart button.
- **Pause:** Game loop halts; grid dims; resume on same key or button.

### AI / Opponent
- None. Purely single-player vs self. [Confirmed]

### Feedback Systems
- **Visual:** Food flash on spawn; brief score pop (+10) floating above eaten food cell; snake head brightens briefly on eat ("eat flash"). Optional: grid flash on death.
- **Audio:** WebAudio SFX — eat chirp, death buzz, move tick (optional/mutable). [Estimated]
- **Haptic:** Not applicable for web. [N/A]

---

## 4. Progression

- **No persistent XP or unlock tree.** Progression is entirely within a single run.
- **Within-run progression:** Snake length increases; speed increases; navigating a longer snake in a tighter space at higher speed constitutes the difficulty ramp.
- **High score:** Stored in localStorage. Displayed persistently as "BEST" in the HUD. [Estimated]
- **No prestige/rebirth.** Restart resets length and score to zero.

---

## 5. Economy & RNG *(tables)*

### Currencies
| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| Score (points) | Single-run soft metric | Eating food (+10 per pellet) | Display only; cannot be spent |
| High score | Persistent record | Best run score | Display only |

No monetization currencies in the base arcade clone. [Confirmed]

### Cost-Scaling
N/A — no economy. [Confirmed]

### Speed Scaling Curve [Estimated]
| Snake Length | Tick Interval (ms) | Approximate FPS |
|---|---|---|
| 3 (start) | 200 | 5 |
| 6 | 185 | 5.4 |
| 10 | 165 | 6.1 |
| 15 | 140 | 7.1 |
| 20 | 115 | 8.7 |
| 30 | 90 | 11.1 |
| 50+ | 65 | 15.4 |
| 80+ | 50 | 20 |

Formula: `interval = max(50, 200 − (length − 3) × 2)` ms. [Estimated — tunable]

### RNG
- **Food placement:** Uniform random selection from all unoccupied cells. [Confirmed]
- No loot tables, gacha, or drop rates. [Confirmed]

---

## 6. Content Inventory *(counts + lists)*

| Category | Count | Detail |
|---|---|---|
| Grid sizes | 1–2 | 20×15 (portrait mobile), 25×20 (landscape desktop) |
| Snake skins | 1 (MVP) | Solid green body, lighter green head |
| Food types | 1 | Single red pellet (apple aesthetic) |
| Game modes | 1 | Classic Arcade |
| Sound effects | 3 | Eat, die, (optional) move tick |
| Music tracks | 0 (MVP) | Silence or optional ambient loop |
| Screens | 5 | Splash/Start, Game, Pause overlay, Game-Over overlay, Settings modal |

No characters, enemies, levels, or storyline content. [Confirmed]

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract monochrome or retro-palette grid. No world or lore.
- **Premise:** None beyond implied survival — steer the snake, eat, don't die.
- **Character:** The snake is an anonymous game entity; no personality or dialogue.
- **Tone:** Minimal, focused, nostalgic retro arcade. Clean pixel-art aesthetic.
- **IP:** Nokia's "Snake" branding is trademarked; clone must use a different name (e.g., "Slither Grid", "Neon Snake", "Pixel Serpent"). Mechanics are public domain. [Confirmed — IP note]
- **Color palette (Estimated):** Dark background (#1a1a2e), neon green snake (#4ade80 / #22c55e), red food (#ef4444), white/gray HUD text. Alternatively retro green-on-black phosphor look.

---

## 8. Meta & Social Systems

- **No meta systems** in the base arcade clone. [Confirmed]
- **High score persistence:** localStorage key `snake_best`. Displayed at all times as "BEST".
- **No daily rewards, missions, events, battle pass, leaderboards, or multiplayer.** [Confirmed]
- **Optional additions (post-MVP):** Online leaderboard via a simple backend; daily challenge (fixed seed RNG); timed mode.
- **Live-ops cadence:** N/A — static arcade game requires no ongoing content operation. [Confirmed]

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|---|---|
| Start Screen | Logo/title, best score, "Tap to Start" / Enter prompt, controls cheat-sheet |
| Game Screen | Canvas rendering grid + snake + food + HUD |
| Pause Overlay | Semi-transparent overlay on game canvas; "PAUSED" text; resume button |
| Game-Over Overlay | Final score, best score (updated if new record), "Play Again" button, brief death animation |
| Settings Modal | Mute toggle, (optional) speed preset, credits |

### Settings / Options Menu Contents
- Sound ON/OFF toggle (persisted in localStorage)
- About / Credits (one line: "Inspired by Nokia Snake, 1997")

### Gameplay HUD Elements
| Element | Position | Detail |
|---|---|---|
| SCORE | Top-left | Current run score (integer) |
| BEST | Top-right | All-time high score from localStorage |
| Pause button | Top-center or corner | Touch-friendly icon |
| D-pad buttons | Bottom of screen (mobile) | 4 directional buttons, large touch targets |

### Navigation Flow
```
Start Screen
    └─[Start]──► Game Screen ──[P/Esc]──► Pause Overlay ──[Resume]──► Game Screen
                     │                                                        │
                     └─[Collision]──► Game-Over Overlay ──[Restart]──► Game Screen
                     └─[Settings icon]──► Settings Modal ──[Close]──► Game/Start Screen
```

### Onboarding / Tutorial (First-Time User Flow)
1. Start screen loads; display title, best score (0 first time), simple arrow-key + swipe icon.
2. Player presses Enter, Space, or taps "Start" — game begins.
3. No interactive tutorial; controls are simple enough to discover in 5 seconds.
4. First food pellet spawns visibly near snake start position to prompt immediate action. [Estimated]
5. Score appears the moment first pellet is eaten — no further instruction needed.

---

## 10. Art, Audio, Camera & Feel

- **Dimension:** 2D top-down.
- **Camera / perspective:** Fixed overhead view; no scrolling; the entire playfield is always visible.
- **Orientation:** Portrait-first on mobile (grid taller than wide); landscape on desktop.
- **Art style:** Retro pixel / flat geometric. Cells are squares with 1px gap. Snake body uses rounded rectangles. Food is a circle with a small highlight dot. [Estimated]
- **Color palette:** Dark navy background, neon green snake body, brighter green head, red food circle, white HUD text, subtle grid lines at 10% opacity. [Estimated]
- **Animation:**
  - Snake moves one grid cell per tick (stepped, not interpolated in MVP).
  - Eat flash: food cell brightens for 80ms before disappearing. [Estimated]
  - Death: brief red flash over entire canvas for 300ms. [Estimated]
  - Score pop: "+10" text floats up and fades over 500ms from eaten cell. [Estimated]
- **VFX:** Minimal — flash and fade only. Optional particle burst on eat for polish.
- **SFX (WebAudio, no files):**
  - **Eat:** Short ascending tone (220→440 Hz, 80ms).
  - **Die:** Descending buzz (300→80 Hz, 300ms with distortion).
  - **Tick:** Optional ultra-soft click per move (20ms, very low gain).
- **Music:** None in MVP (avoids licensing issues and fits retro aesthetic).
- **Juice level:** Low-medium — enough flash/sound to feel responsive without distracting from precision play.

---

## 11. Monetization

**Base arcade clone has zero monetization.** This is appropriate for an offline single-player skill game delivered as a static HTML file. [Confirmed]

If a monetized version were built (web or app store):
| Ad Type | Placement | Frequency |
|---|---|---|
| Rewarded video | "Continue after death?" optional opt-in | Per-death, user-triggered only |
| Interstitial | Between every 3rd game over | Capped at 1 per 3 min |
| Banner | Below canvas | Persistent but low intrusion |

**IAP table (hypothetical):**
| Product | Price | Value |
|---|---|---|
| Remove Ads | $1.99 | Permanent ad removal |
| Skin Pack | $0.99 | 3 alternate snake color themes |

**Loot boxes / gacha:** None appropriate for this game type. [Confirmed]

**Consent / ATT / CMP:** If ads are implemented, ATT prompt (iOS) and GDPR CMP (EU) are required before ad SDK initialization. Age gate (13+) required if targeting < 13. [Confirmed — legal requirement, not implemented in static HTML MVP]

**Aggressiveness:** Minimal in MVP. Any ad layer should be purely opt-in rewarded to avoid destroying the arcade feel.

---

## 12. Retention Hooks

- **High score persistence:** Strongest retention hook — players are motivated to beat their own best. [Confirmed]
- **Idle / offline earnings:** None — this game has no idle mechanics. [Confirmed]
- **Daily rewards / login streaks:** None in base arcade. [Confirmed]
- **Push notifications:** None (static HTML file). [Confirmed]
- **FOMO / energy / lives:** None — unlimited instant restarts keep friction near zero. [Confirmed]
- **Implicit hook:** "One more game" psychology from short session length and instant restart. [Confirmed — classic arcade pattern]

---

## 13. Localization & Accessibility

- **Languages:** Base version English-only; UI text is minimal (Score, Best, Pause, Game Over, Play Again) — trivially localizable. [Estimated]
- **RTL support:** Not required; numeric scores are RTL-safe by default. [Estimated]
- **Text scaling:** HUD uses relative font sizing; readable at all canvas sizes.
- **Colorblind modes:** Current palette (green/red/dark) has green-red conflict for deuteranopia. Recommended fix: add shape differentiation (food = circle vs snake = square) and optionally an alternate palette (blue food). [Estimated]
- **Difficulty / assist options:** Optional speed presets (Slow / Normal / Fast) selectable in settings. [Estimated]
- **Subtitle / audio options:** Mute toggle covers all audio needs.
- **Regional differences:** None. [Confirmed]
- **Age/content rating:** IARC Everyone / PEGI 3. No violence, no user data collection in MVP. COPPA compliance: no data collected, no accounts, no ads in base version — fully compliant. [Estimated]

---

## 14. Technical Structure

- **Engine / framework:** Vanilla JavaScript + HTML5 Canvas API. No framework, no build step. [Confirmed for this clone]
- **Language:** HTML5 + CSS3 + ES6+ JavaScript.
- **Platforms:** Any modern browser (Chrome, Firefox, Safari, Edge). Desktop and mobile.
- **Online / offline:** Fully offline-capable. No network calls. [Confirmed]
- **Save system:** localStorage for high score and mute preference. Key: `snake_best`, `snake_mute`. [Estimated]
- **Accounts / auth:** None. [Confirmed]
- **Cross-device sync:** None. [Confirmed]
- **Multiplayer netcode:** N/A — single-player. [Confirmed]
- **Anti-cheat / server authority:** N/A — single-player offline game. [Confirmed]
- **Backend services:** None. [Confirmed]
- **Analytics:** None in base version.
- **Third-party SDKs:** None — zero external dependencies. [Confirmed]
- **App size:** ~15–25 KB (single HTML file with inline JS/CSS). [Estimated]
- **Performance:** Canvas renders at 60fps via requestAnimationFrame; game logic ticks at variable interval (50–200ms). Minimal CPU load. [Estimated]
- **Game loop pattern:** requestAnimationFrame drives animation (score pop, flash effects); setInterval or timestamp-delta drives snake movement ticks.

---

## 15. Pacing & Difficulty

### Early Game (length 3–10)
- Slow tick (185–165ms), wide open grid. Almost impossible to die. [Estimated]
- Tutorial-equivalent: player discovers controls and the eat-to-grow loop.
- "Aha moment": first few pellets eaten; score counting up; snake visibly growing.

### Mid Game (length 10–30)
- Speed ramps (165→90ms). Grid becomes meaningfully crowded.
- Players must plan paths 2–4 cells ahead. Accidental self-collision becomes common.
- Score 100–270 range. Engagement peak.

### Late Game (length 30+)
- Tick < 90ms. Snake occupies 20%+ of grid. Navigation requires deliberate spiraling strategies.
- Score 300+. Very few players reach this stage organically.
- "Wall" moment: snake length ~60–70 on a 20×15 grid; nearly impossible to maneuver.

### Difficulty Curve
`interval = max(50, 200 − (length − 3) × 2)` ms caps at 50ms (~20 ticks/sec), which is the theoretical maximum playable speed. [Estimated]

### Churn Points
1. **First few games:** Players who don't understand 180° reversal die immediately on reversal attempt — guard must be visible or forgiving.
2. **Length ~15:** Speed jump feels sudden. Players quit here if ramp is too steep.
3. **Length ~30:** Grid feels overwhelming; most casual players stop. [Estimated — based on genre norms]

---

## 16. Clone Build Plan

### MVP Feature Set
- [x] Single HTML file, canvas renderer
- [x] 20×15 grid, wall-death mode
- [x] Snake movement + growth
- [x] Food spawn (random empty cell)
- [x] Self and wall collision detection
- [x] Speed scaling by length
- [x] 180° reversal guard
- [x] Score (+10/pellet) + localStorage high score
- [x] Start / Pause / Game-Over screens
- [x] Keyboard controls (arrows + WASD)
- [x] Mobile D-pad buttons + swipe detection
- [x] Responsive canvas scaling
- [x] WebAudio eat + die SFX + mute toggle
- [x] Eat flash + score pop animation
- [x] Death canvas flash

### Full Feature Set (post-MVP)
- [ ] Multiple grid size presets / difficulties
- [ ] Alternate snake/food skins (palette swap)
- [ ] Online leaderboard (simple backend)
- [ ] Daily challenge mode (seeded RNG)
- [ ] Timed mode (60-second sprint)
- [ ] Two-player split-keyboard mode
- [ ] Bonus food (flashing, higher value, disappears after timeout)
- [ ] Power-ups (slow-down, invincibility for 3 seconds)
- [ ] PWA manifest + service worker (installable offline app)

### Phased Roadmap
| Phase | Scope | Est. Time |
|---|---|---|
| Phase 1 | Core engine: grid, snake movement, food, collision, score, speed | 2–3 hrs |
| Phase 2 | UI: start/pause/game-over screens, HUD, localStorage best | 1–2 hrs |
| Phase 3 | Input: keyboard + D-pad + swipe, 180° guard | 1 hr |
| Phase 4 | Responsive layout, canvas scaling, mobile viewport | 1 hr |
| Phase 5 | Juice: WebAudio SFX, eat flash, score pop, death flash, mute | 1–2 hrs |
| Phase 6 | QA: collision edge cases, restart state reset, cross-browser, touch | 1 hr |

**Total MVP:** ~7–10 hours solo development.

### Recommended Tech Stack
- HTML5 Canvas (2D context) — no WebGL needed
- Vanilla ES6+ JavaScript — no frameworks
- CSS3 for overlay screens and D-pad buttons
- WebAudio API for SFX generation (no audio files)
- localStorage for persistence

### Required Asset List
- **Art:** None — all drawn procedurally with Canvas API (rectangles, arcs, fillStyle). [Confirmed]
- **Audio:** None — all generated via WebAudio OscillatorNode. [Confirmed]
- **Data tables:** Speed curve table (hardcoded constants), score increment constant.
- **Fonts:** System font stack or single Google Font (load from CDN only if online; use system-ui for offline).

### Hardest Parts / Risks
1. **Responsive canvas scaling with crisp pixel rendering** — must handle window resize, devicePixelRatio, and portrait/landscape transitions without blurry cells.
2. **Touch input latency** — swipe detection must distinguish tap (turn) from scroll; D-pad buttons must prevent default scroll events.
3. **Input queue / buffer** — rapid direction presses within one tick can cause reversal if not properly queued; must only queue one pending direction, not stack multiple.
4. **Restart state reset** — all game state (snake array, direction, food position, score, timers, pending animation frames) must fully reset on replay without memory leaks.
5. **Speed cap feel** — at max speed (50ms), the game can feel unfair; may need a visual warning when entering "danger speed" to feel skill-based rather than arbitrary.

---

## 17. Open Questions

1. **Optimal speed cap:** 50ms feels very fast on mobile; playtesting may suggest 65–80ms as a better cap for casual audiences. [Estimated — needs playtesting]
2. **Grid resolution on very small phones:** A 20×15 grid on a 320px-wide screen gives 16px cells — borderline for finger accuracy on the D-pad. May need to reduce to 16×12 below 360px viewport width. [Estimated]
3. **Score pop legibility:** Floating "+10" text at high speed may be unreadable; could be dropped if it feels distracting during playtesting.
4. **Bonus food:** Whether to add a secondary flashing food pellet as a risk/reward mechanic is a meaningful design question that changes the game balance significantly — left to the builder's discretion.

---

*Blueprint complete. All 17 sections filled. Economy confirmed as score-only with no currencies or RNG beyond food placement. Build plan phased and risk-flagged.*
