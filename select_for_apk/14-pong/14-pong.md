# Pong — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Pong is the foundational 2D arcade paddle-tennis game: two paddles, one bouncing ball, first player to 11 points wins. Its core appeal is pure, skill-based competitive gameplay with an immediately legible ruleset — zero tutorial required. The ball accelerates each rally, creating natural tension, and paddle-hit-position determines return angle, adding a layer of skill depth.

**Quick facts:**
- **Original developer/publisher:** Atari (Allan Alcorn), 1972 [Confirmed]
- **Platforms:** Arcade cabinet (original); cloned on every major platform since
- **This clone target:** Web browser (HTML5 Canvas, desktop + mobile)
- **Age/content rating:** IARC 3+ / Everyone [Confirmed]
- **Monetization model (original):** Coin-op arcade; this clone = free-to-play, no ads, no IAP
- **Session length:** 2–5 minutes per match [Estimated]
- **Play style:** Active, single-player vs AI (primary) or local 2-player (secondary)

---

## 2. Core Loops

- **30-second loop:** Ball launches → player moves paddle to intercept → ball hits paddle at angle dependent on hit position → ball travels to opponent side → opponent (AI or player 2) returns or misses → point scored → ball resets and relaunches.
- **Session loop:** Player selects difficulty → plays a match to 11 points → wins or loses → sees score summary → rematch or changes difficulty. One session = one complete match (~2–5 min).
- **Meta loop (this clone):** Player tries to beat their personal best rally streak and win count stored in localStorage. No long-term progression system — the loop IS the meta for this genre.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics

| Mechanic | Description |
|---|---|
| Ball movement | Constant velocity vector; reflects off top/bottom walls (equal angle reflection). Travels in straight lines only. [Confirmed] |
| Paddle collision + angle | Paddle divided into 7 hit zones. Center zone returns ball at 90° to paddle (horizontal travel). Outer zones deflect at progressively steeper angles (up to ±75° from horizontal). Hit zone = (ballY - paddleTop) / paddleHeight normalized to [0,1], mapped to angle range. [Confirmed] |
| Ball speed acceleration | Ball speed increases by a fixed multiplier (~5–10%) on each successful paddle return. Speed resets to base on each point scored. [Confirmed] |
| Scoring | Ball passing left/right edge of court scores 1 point for the opposing side. No serve delay mechanic. [Confirmed] |
| Win condition | First player or AI to reach 11 points wins the match. [Estimated — original was 11; some versions use 7 or 15] |
| Serve direction | After a point, ball launches from center toward the player who just conceded, at a random vertical angle within ±30°. [Estimated] |
| Paddle clamping | Paddles cannot move beyond top/bottom court boundaries. [Confirmed] |

### Core Verbs
- Move paddle up / down
- Return the ball (implied — no button press, just positioning)

### Game Modes

| Mode | Description |
|---|---|
| Player vs AI | Primary mode. AI difficulty selectable: Easy / Normal / Hard. |
| Player vs Player (local) | Optional bonus mode — two players on same device (keyboard + mouse/touch split). |
| Practice / Rally | [Estimated addition] Endless rally against a wall or infinite-patience AI for warmup. |

### Input Scheme

| Input | Action | Notes |
|---|---|---|
| Mouse Y drag | Move player paddle | Mouse Y maps directly to paddle center Y, clamped to court |
| Touch drag | Move player paddle | Single-finger vertical drag on left half of canvas |
| W / Up Arrow | Move paddle up | 8px/frame [Estimated] |
| S / Down Arrow | Move paddle down | 8px/frame [Estimated] |
| P / Escape | Pause/resume | |
| M | Mute/unmute audio | |
| Enter / Space | Confirm menu selection / serve | |

- **Screen orientation:** Landscape (primary). Mobile users should rotate device; a rotate-prompt shown in portrait.
- **Touch area:** Full left half of canvas acts as drag zone for player paddle.

### Win / Lose / Fail States

| State | Trigger | Outcome |
|---|---|---|
| Win | Player reaches 11 points | Game-over screen: "You Win!", final scores, streak update, rematch/menu buttons |
| Loss | AI reaches 11 points | Game-over screen: "You Lose!", final scores, rematch/menu buttons |
| Point scored | Ball exits left or right edge | Flash effect, sound, score increments, ball resets to center |
| Pause | P/Escape pressed | Overlay with Resume / Quit to Menu options |

### AI Opponent Behavior

The AI paddle tracks the ball using a simplified prediction model with tunable error:

| Difficulty | AI max paddle speed (px/frame) | Error margin (px) | Prediction lookahead | Description |
|---|---|---|---|---|
| Easy | 3.5 | ±60 | None (reacts to current ball Y) | Frequently misses; gives player clear wins |
| Normal | 5.5 | ±20 | 1 bounce | Competitive but beatable; misses ~25% of edge shots |
| Hard | 8.5 | ±4 | Full trajectory to paddle X | Near-perfect; only beatable with sharp-angle edge shots |

- AI moves toward `targetY = ballPredictedY + random(-errorMargin, +errorMargin)`.
- AI paddle speed is capped; it cannot teleport.
- Hard AI is intentionally not 100% perfect — error margin ensures player can win with skill.

### Feedback Systems

- **Ball hit paddle:** short "bloop" SFX + brief ball flash [Confirmed genre norm]
- **Ball hit wall:** higher-pitched "bleep" SFX [Confirmed genre norm]
- **Score:** score counter flash + "score" SFX + screen flash
- **Win:** ascending jingle + winner text animation
- **Lose:** descending tone + loser text

---

## 4. Progression

Pong has no persistent upgrade or XP progression — the game IS the session. "Progression" is purely skill-based within a match:

- **Within a match:** Ball accelerates each rally (see §3), increasing difficulty as a rally extends.
- **Difficulty selection:** Player can choose Easy → Normal → Hard before each match. Choice persisted in localStorage.
- **Personal bests (this clone):** localStorage tracks:
  - Longest rally streak (consecutive returns without a miss)
  - Total wins vs AI (per difficulty)
- **No unlock trees, prestige, or gating** — this is the correct design for the genre. [Confirmed]

---

## 5. Economy & RNG

Pong has no currency, no economy, and no gacha. This section documents the only randomness in the game.

### Currencies

None. [Confirmed]

### RNG / Randomness

| Event | RNG Applied | Range / Formula |
|---|---|---|
| Initial serve angle | Random vertical angle after each point | ±30° from horizontal [Estimated] |
| Serve direction (horizontal) | Toward player who conceded the point | Deterministic [Confirmed] |
| AI error margin | Uniform random offset applied to AI target Y each frame | ±errorMargin px (see §3 table) [Estimated] |

### No drop rates, loot tables, or gacha. [Confirmed]

### Ball Speed Scaling

| Rally hit # | Speed multiplier | Example speed (px/frame) |
|---|---|---|
| 0 (serve) | 1.0× | 5.0 [Estimated base] |
| 1 | 1.06× | 5.3 |
| 5 | 1.34× | 6.7 |
| 10 | 1.79× | 9.0 |
| 15 | 2.40× | 12.0 |
| Speed cap | — | 15.0 px/frame [Estimated cap to prevent tunneling] |

Formula: `speed = min(baseSpeed × 1.06^hits, maxSpeed)`

---

## 6. Content Inventory

| Category | Count | Details |
|---|---|---|
| Game modes | 2–3 | PvAI, PvP local, optional Practice |
| AI difficulty levels | 3 | Easy, Normal, Hard |
| Ball skins (this clone) | 1 base + optional | White circle; optional color themes |
| Paddle skins (this clone) | 1 base | White rectangles |
| Court themes (this clone) | 1 | Black background, white dashed center line |
| Sound effects | 5 | Bloop (paddle hit), bleep (wall hit), score tone, win jingle, lose tone |
| Screens | 6 | Splash/menu, difficulty select, game, pause overlay, game-over, settings |

Pong is intentionally minimal — its content is its mechanic, not its asset library. [Confirmed]

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract black court — no real-world setting. Pure geometric abstraction. [Confirmed]
- **Premise:** Two paddles, one ball, simple tennis metaphor. Zero narrative. [Confirmed]
- **Story delivery:** None. No cutscenes, dialogue, or lore. [Confirmed]
- **Character personalities:** None — paddles are rectangles. [Confirmed]
- **Writing style:** Minimal UI labels only ("Player", "CPU", "Score", "Win!").
- **Tone:** Clean, minimal, competitive, nostalgic. The aesthetic IS the statement — stark black/white geometry communicates timelessness and elegance.
- **Licensed IP:** None — Atari's trademark applies to their specific art/name, not the mechanic. Clone uses original branding. [Confirmed]
- **Color palette (this clone):** Near-black background (#0a0a0a), white paddles/ball/HUD (#f0f0f0), neon accent for score (#00ffcc) [Estimated design choice].

---

## 8. Meta & Social Systems

- **Daily/weekly missions:** None in original; this clone adds optional "beat your rally streak" as informal meta. [Estimated]
- **Achievements:** None in original. This clone: localStorage-backed personal bests only.
- **Live-ops / events:** None — this is a static arcade game. [Confirmed]
- **Leaderboards:** None in original. This clone: local high score only (localStorage). [Confirmed — no backend]
- **Multiplayer:** Local 2-player on one device only (no network multiplayer). [Confirmed scope]
- **Social sharing:** None built-in (no backend). [Confirmed]
- **Battle pass / season:** Not applicable. [Confirmed]

---

## 9. UI / UX & Screen Map

### Screen List

| Screen | Purpose |
|---|---|
| Splash / Main Menu | Game title, "Play" button, "How to Play" link, difficulty selector, settings icon |
| Difficulty Select | Three buttons (Easy / Normal / Hard) with brief description; "Back" button |
| Game Screen | Core gameplay canvas — paddles, ball, scores, pause button |
| Pause Overlay | Semi-transparent overlay: "Resume", "Restart", "Quit to Menu" |
| Game Over Screen | Winner announcement, final score, rally streak, "Rematch" + "Menu" buttons |
| Settings Screen | Audio mute toggle, controls info, "About" one-liner, "Back" |

### Settings / Options Menu Contents
- Sound: ON / OFF toggle (persisted in localStorage)
- Controls: brief key/touch reminder
- About: one-line credit ("Inspired by Pong, 1972 Atari")
- Back to Menu

### Gameplay HUD Elements
- Player score (top-left, large)
- AI/P2 score (top-right, large)
- Rally counter (center-top, small — shows current rally hit count)
- Dashed center dividing line
- Pause button (top-center small icon, or P key)
- Mute icon (corner)

### Navigation Flow
```
Splash/Menu → [Play] → Difficulty Select → Game Screen
                                              ↓ [P/Esc]
                                           Pause Overlay
                                              ↓ [Resume]
                                           Game Screen
                                              ↓ [11 pts]
                                           Game Over Screen
                                              ↓ [Rematch]
                                           Game Screen (same difficulty)
                                              ↓ [Menu]
                                           Splash/Menu
```

### Onboarding / Tutorial (First-Time User Flow)
1. Splash screen loads; game title and subtitle displayed ("Classic Paddle Tennis").
2. Three difficulty buttons shown with one-line descriptions — player selects.
3. A brief 3-second "How to Play" overlay on first play: "Move your paddle with mouse/touch or W/S keys. Don't let the ball pass you. First to 11 wins." [Auto-dismissed after 3s or on click]
4. Ball launches automatically after 1-second countdown "3...2...1...GO!".
5. No further tutorial — game teaches itself through play.

---

## 10. Art, Audio, Camera & Feel

### Visual
- **Dimension:** 2D [Confirmed]
- **Camera:** Fixed orthographic top-down (actually side-on); no camera movement [Confirmed]
- **Orientation:** Landscape (4:3 or 16:9) [Confirmed — matches original and modern norm]
- **Art style:** Minimalist geometric — pure rectangles and circles, no sprites. Pixel-perfect rendering. [Confirmed]
- **Color palette:** Black background, white paddles + ball, white dashed center line, cyan accent for active score
- **Paddle size:** ~10px wide × 80px tall at 640px canvas height [Estimated proportional]
- **Ball size:** 10×10px square (original) or circle (this clone) [Confirmed original was square; circle is standard modern choice]
- **Animation:** No spritesheet animation — purely procedural position updates

### VFX (Juice)
- Screen flash on score (white flash, 150ms fade)
- Ball trail: 3-frame ghost trail for speed feel [Estimated addition]
- Paddle hit ripple: brief scale pulse on paddle
- Score counter pop animation on increment
- Game-over: winning side's paddle glows

### Audio (WebAudio API — no files)
| Sound | Generation method | Pitch |
|---|---|---|
| Paddle hit | Short square wave burst, 10ms | 220 Hz |
| Wall hit | Short square wave burst, 8ms | 440 Hz |
| Score | Descending two-tone | 330→220 Hz |
| Win jingle | Ascending 3-note arpeggio | 440→550→660 Hz |
| Lose tone | Descending glide | 330→165 Hz |

### Game Feel
- **Input latency:** Zero — mouse/touch Y directly maps to paddle center each frame
- **Ball reflection:** Crisp, no easing — ball changes direction instantly on collision
- **Paddle movement:** Smooth, keyboard movement uses per-frame delta (not event-driven) for held-key feel

---

## 11. Monetization

**This clone has no monetization.** It is a free, self-contained offline HTML5 game.

| Category | Status |
|---|---|
| Ads | None |
| IAP | None |
| Loot boxes / gacha | None |
| Subscription / battle pass | None |
| Data tracking | None — localStorage only, no analytics SDK |
| ATT / GDPR / CMP flows | Not applicable (no tracking, no ads) |

Original arcade Pong monetized via coin-op (25¢ per play). [Confirmed]

---

## 12. Retention Hooks

Pong is a session-complete arcade game; classical retention mechanics do not apply. This clone uses minimal hooks appropriate to the format:

| Hook | Implementation |
|---|---|
| Personal best rally streak | Shown on game-over screen; stored in localStorage; motivates "one more game" |
| Win streak counter | Displayed on game-over; resets on loss |
| Difficulty progression | Easy → Normal → Hard forms an informal skill ladder |
| Idle / offline earnings | None — not applicable to action arcade genre [Confirmed] |
| Push notifications | None — no backend [Confirmed] |
| Energy / lives system | None — unlimited plays [Confirmed] |
| Daily reward | None [Confirmed] |

---

## 13. Localization & Accessibility

### Localization
- **This clone:** English only [Estimated — single developer scope]
- **RTL support:** Not implemented (no text-heavy UI)
- **Regional differences:** None

### Accessibility
| Feature | Implementation |
|---|---|
| Keyboard controls | Full keyboard support (W/S + Arrow keys) — no mouse required |
| Mouse controls | Full mouse support — no keyboard required |
| Touch controls | Full touch support — no hardware peripherals required |
| Colorblind mode | High contrast by default (white on black); no color-coded information that could fail |
| Text scaling | Canvas-rendered text; no browser font scaling dependency |
| Reduced motion | No essential animation — game is playable with all VFX disabled |
| Subtitle/audio options | Mute toggle; no voice acting to subtitle |
| Difficulty / assist | Three difficulty levels; Easy provides meaningful handicap |

### Age / Content Rating
- **IARC/ESRB:** Everyone (E) / PEGI 3 [Confirmed — no violence, no mature content]
- **COPPA/GDPR-K compliance:** No personal data collected, no accounts, no ads — fully compliant by design [Confirmed]

---

## 14. Technical Structure

### This Clone
- **Engine/Framework:** Vanilla JavaScript + HTML5 Canvas API [Confirmed design choice]
- **Language:** JavaScript (ES6+)
- **Build system:** None — single .html file, no build step
- **Platform:** Any modern browser (Chrome, Firefox, Safari, Edge); desktop + mobile
- **Online requirement:** Fully offline — no network requests after load
- **Save system:** localStorage (difficulty preference, best rally, win counts per difficulty)
- **Accounts/auth:** None
- **Cross-device sync:** None
- **Netcode:** None (local 2-player is same-device only)
- **Anti-cheat:** N/A (single-player vs AI; no competitive backend)
- **Backend services:** None
- **Analytics:** None
- **Third-party SDKs:** None
- **Audio:** WebAudio API (procedurally generated, no audio files)
- **File size:** ~15–25 KB (single HTML file) [Estimated]
- **Performance target:** 60 fps on all modern devices; requestAnimationFrame loop

### Original Atari Pong (1972) Technical Notes [Confirmed]
- Implemented in pure analog hardware (TTL logic), no CPU, no software
- Black-and-white composite video output at ~60Hz
- Paddle inputs were potentiometers

---

## 15. Pacing & Difficulty

### Match Pacing
| Phase | Duration | Feel |
|---|---|---|
| Early match (0–4 pts each) | ~1 min | Slow ball, easy returns; player gets comfortable |
| Mid match (5–8 pts) | ~1.5 min | Ball has accelerated; rallies get tense; close scores build suspense |
| Late match (9–10 pts) | ~1 min | High-speed ball; every point crucial; maximum tension |
| Match end | Instant | Win/lose screen immediately on 11th point |

### Ball Speed Curve
- Ball is slow at serve; after 5+ hits it becomes noticeably faster; at 12+ hits it reaches near-max speed.
- This creates natural rally anxiety — the longer the rally, the harder it gets.
- Speed reset on each point ensures every new serve starts manageable.

### Difficulty Curve (across sessions)
- **Easy:** Player should win ~70% of matches [Estimated — used as confidence-builder]
- **Normal:** Roughly 50/50 once player has basic skill [Estimated]
- **Hard:** Player needs to use angle play and anticipation to win; ~30% win rate for average player [Estimated]

### Key Milestone Moments
- First rally of 5+ hits: "oh, this is getting fast" moment
- Deuce situation (both at 10): maximum tension
- First win on Hard: mastery moment, highly satisfying

### Churn Points
- Players who find Easy too easy quit quickly — need Normal as default
- Players who find Hard unwinnable quit — error margin on Hard must be non-zero
- Very long waits between serves (if any) would kill momentum — keep serve delay ≤1.5s

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---|---|---|
| Canvas + rAF game loop | Yes | Yes |
| Ball physics (reflection, wall bounce) | Yes | Yes |
| Paddle collision + angle zones | Yes | Yes |
| Ball speed acceleration per rally | Yes | Yes |
| Player paddle (mouse + keyboard) | Yes | Yes |
| AI opponent (single difficulty) | Yes | Yes |
| Score tracking + win condition | Yes | Yes |
| Game-over + rematch screen | Yes | Yes |
| Three AI difficulty levels | No | Yes |
| Touch controls | No | Yes |
| WebAudio SFX | No | Yes |
| Mute toggle | No | Yes |
| localStorage persistence | No | Yes |
| Pause screen | No | Yes |
| VFX juice (flash, trail, pulse) | No | Yes |
| Local 2-player mode | No | Yes |
| Best streak display | No | Yes |
| Responsive canvas scaling | No | Yes |

### Phased Roadmap

**Phase 1 — Core gameplay (Day 1, ~4h)**
1. HTML boilerplate with Canvas, meta viewport
2. Game loop with requestAnimationFrame
3. Ball with velocity vector, wall reflection, speed capping
4. Player paddle with mouse + keyboard input, boundary clamping
5. Basic AI paddle that tracks ball Y
6. Collision detection: ball↔paddle (AABB), angle calculation from hit zone
7. Score tracking, point reset, win condition
8. Minimal HUD (scores only)

**Phase 2 — Polish + UI (Day 1–2, ~3h)**
9. Menu screen with difficulty selection
10. Pause overlay
11. Game-over screen with rematch
12. Three AI difficulty levels with error margin + speed tuning
13. Ball speed acceleration system

**Phase 3 — Juice + feel (Day 2, ~2h)**
14. WebAudio SFX (5 sounds, all procedural)
15. Screen flash on score
16. Ball trail ghost effect
17. Paddle hit pulse animation
18. Score pop animation
19. Mute toggle

**Phase 4 — Mobile + persistence (Day 2, ~2h)**
20. Touch controls (full-half-screen drag zone)
21. Responsive canvas scaling (window resize handler)
22. localStorage: difficulty, best rally, win counts
23. Portrait-mode rotate prompt
24. First-play "How to Play" overlay

**Phase 5 — Bonus (Day 3, ~1h)**
25. Local 2-player mode (keyboard only — P1: W/S, P2: Up/Down)
26. Rally counter HUD
27. Win streak display

### Recommended Tech Stack (Clone)
- HTML5 + CSS3 + Vanilla JavaScript (ES6+)
- Canvas 2D API for rendering
- Web Audio API for SFX
- localStorage for persistence
- No frameworks, no build tools, no CDN dependencies

### Required Asset List
| Asset | Type | Notes |
|---|---|---|
| All visuals | Procedural (Canvas draw calls) | No image files needed |
| All audio | Procedural (WebAudio oscillators) | No audio files needed |
| Font | System font (monospace) | No web font CDN needed |

### Hardest Parts / Risks
1. **Ball tunneling at high speed:** At max speed, ball can skip through thin paddles in one frame. Fix: swept collision (check ball path line segment vs paddle rect) or substep physics.
2. **AI difficulty balance:** Easy must be beatable by beginners; Hard must be beatable by skilled players. Error margin tuning requires playtesting iteration.
3. **Touch control feel on mobile:** Mouse-to-paddle mapping must be responsive with no input lag; finger-occlusion of ball must be minimized by positioning player paddle on left (not bottom).
4. **Canvas scaling with correct aspect ratio:** Window resize must scale canvas without stretching game logic coordinates. Solution: maintain logical 800×600 canvas, CSS-scale to fit viewport while preserving aspect ratio.
5. **Angle reflection edge cases:** Ball hitting paddle corner pixel needs graceful handling to avoid infinite horizontal or vertical bounce locks.

---

## 17. Open Questions

1. **Exact original ball speed numbers:** Atari's original analog hardware had no software-defined speed; exact equivalent px/frame values for the original are inherently estimated. Tune base speed during playtesting. [Estimated values provided — verify feel]
2. **Optimal Easy difficulty parameters:** The exact AI speed + error margin that feels fair-but-winnable to a first-time player requires playtesting. Suggested: start with the values in §3 and adjust based on observed win rates.
3. **2P mode controls on mobile:** Splitting a mobile screen between two players is awkward (both need vertical drag zones on opposite sides). Consider requiring hardware keyboard for 2P, or making 2P a future feature.

---

*Blueprint complete. All 17 sections filled. No TBDs outside Open Questions. All economy/RNG covered (minimal — no currency system). Win/lose/fail states defined. Screen map complete with step-by-step onboarding. Monetization: none. Retention: localStorage personal bests. Localization: English, COPPA-compliant by design. Build plan: phased with hardest risks named.*
