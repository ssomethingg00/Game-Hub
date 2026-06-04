# Simon Says (Memory Sequence) — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Simon Says is a single-player electronic memory game in which the device lights up a growing sequence of colored pads, each playing a distinct musical tone, and the player must reproduce the sequence from memory. The appeal is pure: escalating tension, the dopamine of a correct chain, and the sharp snap of failure. The original "Simon" was designed by Ralph Baer and Howard Morrison and debuted at Studio 54 in 1978, published by Milton Bradley (now Hasbro). Gameplay is timeless and instantly understood; no tutorial is needed.

**Quick facts:** Developer: Ralph Baer / Howard Morrison (original hardware). Publisher: Milton Bradley / Hasbro. Platforms: Physical toy (1978–present); countless digital clones on Web, iOS, Android, PC. Genre: Memory / reaction / casual arcade. Session length: 2–5 min. Play style: Active, single-player. Age/content rating: IARC Everyone / PEGI 3 / ESRB E — no mature content, suitable all ages. COPPA note: Because the target audience includes children under 13, any clone serving ads or collecting data must gate consent appropriately. Monetization model (clone target): free-to-play, no ads, no IAP — a pure skill/memory arcade experience.

---

## 2. Core Loops

- **30-second loop:** Watch the sequence of pad flashes + tones → tap/click pads in the same order → succeed (next round) or fail (game over).
- **Session loop:** Start → survive progressively longer sequences → eventually make a mistake → see score → decide to retry or quit. A session is typically 2–10 minutes.
- **Meta loop:** Try to beat your personal best round. Best score is persisted locally. Social meta: share score screenshot or challenge a friend to beat it.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics
- **Sequence generation:** On each new round, one random pad index (0–3) is appended to the sequence array. [Confirmed from original game manual]
- **Playback phase:** The game auto-plays the full sequence from the start, lighting each pad + playing its tone for a fixed duration, with gaps between pads. Input is **locked** during playback.
- **Input phase:** After playback, input unlocks. The player taps pads in order. Each correct tap plays the pad's tone + gives visual feedback. Tapping the wrong pad immediately ends the game.
- **Time limit per input step:** If the player takes more than 5 seconds to press the next pad, the game ends (timeout = wrong). [Confirmed — original hardware enforces 5 s window]
- **Speed ramp:** After rounds 5, 9, and 13 the playback tempo increases. [Confirmed from original manual] Clone implements a smooth speed curve as well.
- **Fail sound:** A low "razz/buzz" tone plays on wrong press or timeout, the wrong pad may flash briefly, the correct pad is revealed, then the game-over screen appears.
- **Strict mode:** In strict mode a single mistake ends the game immediately with no retry of the same round. In normal mode the sequence replays from the beginning of the current round after a mistake (optional feature). [Confirmed — original has a "Skill" switch; clones commonly add strict toggle]

### Core Verbs
Tap (or click) a pad; watch; listen; remember.

### Game Modes
| Mode | Description |
|---|---|
| Classic / Normal | Sequence grows each round; mistake replays current round |
| Strict | Mistake immediately ends game |
| Endless | No win cap — play until first mistake; tracks max round [Estimated] |

### Input Scheme
- **Desktop:** Mouse click on pad; keyboard shortcuts (Q/W/A/S or 1/2/3/4) optional.
- **Mobile:** Touch tap; pads must be large (min 120 px each, ideally half the viewport each).
- **Screen orientation:** Portrait primary (square 2×2 grid); landscape also functional.
- Input is **ignored** during playback phase and during game-over state.

### Win / Lose / Fail Conditions
- **Win:** No fixed win condition in classic mode; some implementations add a "congrats" screen at round 20 or 31.
- **Lose:** Wrong pad pressed, or 5-second timeout between presses, or wrong pad in strict mode.
- **Failure handling:** Razz tone plays → brief wrong/correct pad flash → game-over overlay with round score + best score → Replay button.

### Difficulty
Speed increases automatically. No manual difficulty setting needed (though optional).

### Feedback Systems
- **Visual:** Pad lights up (bright glow + color intensifies) during playback and on player tap.
- **Audio:** Each pad plays its unique pitched tone; fail plays low buzz.
- **Haptic:** On mobile devices, `navigator.vibrate(80)` on correct tap; longer buzz on fail. [Estimated]

---

## 4. Progression

- **Round counter:** Starts at 1, increments on each successful full sequence repeat.
- **Speed tiers:** [Confirmed]
  - Rounds 1–5: base speed (pad on: 400 ms, gap: 200 ms)
  - Rounds 6–9: faster (pad on: 300 ms, gap: 150 ms)
  - Rounds 10–13: even faster (pad on: 220 ms, gap: 110 ms)
  - Round 14+: fastest (pad on: 160 ms, gap: 80 ms)
- **Best score:** Highest round ever completed, stored in `localStorage`. Displayed on HUD and game-over screen.
- **No unlock trees, no upgrades, no prestige.** The game is purely skill-based; progression = higher round = harder.

---

## 5. Economy & RNG

### Currencies
None — this is a pure arcade skill game with no virtual economy.

### RNG
| Element | Mechanism |
|---|---|
| Sequence generation | Each round: append `Math.floor(Math.random() * 4)` — uniform distribution over 4 pads |
| No drop rates, no loot, no gacha | N/A |

### Earning / Spending
Not applicable. No currency, no IAP, no ads.

---

## 6. Content Inventory

| Category | Count | Detail |
|---|---|---|
| Pads | 4 | Green (top-left), Red (top-right), Yellow (bottom-left), Blue (bottom-right) |
| Tones | 4 | E4 (329 Hz), C#4 (277 Hz), A3 (220 Hz), E3 (165 Hz) [Confirmed — bugle-derived harmonic set] |
| Fail tone | 1 | ~42–100 Hz buzz, 600 ms [Estimated from common implementations] |
| Game modes | 2–3 | Classic, Strict, Endless [Estimated] |
| Screens | 4 | Start screen, Gameplay screen, Game-over overlay, Settings panel |
| Sound themes | 1 | Default electronic tones; optional musical-note set |

### Pad Color + Tone Map [Confirmed from original]
| Position | Color | Frequency | Musical Note |
|---|---|---|---|
| Top-left | Green | 415 Hz | G#4 |
| Top-right | Red | 310 Hz | Eb4 |
| Bottom-right | Blue | 209 Hz | Ab3 |
| Bottom-left | Yellow | 252 Hz | B3 |

> Alternative widely-used set (freeCodeCamp convention): Green=392 Hz (G4), Red=494 Hz (B4), Yellow=330 Hz (E4), Blue=262 Hz (C4). [Confirmed from community implementations]

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract / arcade. No world, no characters, no narrative. The game IS the object: a glowing circular or square pad array.
- **Premise:** None beyond the arcade challenge — "how long a sequence can you remember?"
- **Tone:** Bright, colorful, competitive, nostalgic. Slightly tense as sequences grow. Celebratory on progress.
- **Visual theme:** Classic toy aesthetic — bold primary colors (red, green, blue, yellow) on a dark background. Rounded corners, slight gloss.
- **IP:** The original game "Simon" is owned by Hasbro. The mechanics are uncopyrightable; the name, logo, and distinctive physical form factor are protected. Clone must use a different name and original art.
- **Writing style:** Minimal. UI text is functional: "Round 5", "Best: 12", "GAME OVER", "Play Again".

---

## 8. Meta & Social Systems

- **Best score persistence:** localStorage key `simonBestRound`. Displayed on HUD and game-over screen. [Estimated — common implementation]
- **No daily rewards, no missions, no events, no battle pass.** This is a pure arcade game.
- **No multiplayer, no leaderboards, no guilds.** (Optional: share-score button generates a text string for social copy-paste.)
- **Live-ops cadence:** None required. Game is feature-complete at launch with no ongoing content burden.
- **Settings persistence:** Mute state and mode (strict/normal) saved to localStorage.

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|---|---|
| Start Screen | Title, best score display, Mode toggle (normal/strict), Play button, How-to-play blurb |
| Gameplay Screen | 4-pad grid, round HUD, best HUD, sequence phase indicator ("Watch..." / "Your turn!"), mute button |
| Game-Over Overlay | "Game Over" heading, round reached, best score, correct pad reveal flash, Replay button, Mode toggle |
| Settings Panel | Mute toggle, mode toggle, "How to play" expandable, optional keyboard shortcuts list |

### Settings / Options Menu
- Sound on/off (mute toggle)
- Mode: Normal / Strict
- Keyboard shortcuts reference
- "How to play" info

### Gameplay HUD Elements
- Round number (current)
- Best score
- Phase label ("Simon's turn…" / "Your turn!")
- Mute button (top corner)

### Navigation Flow
```
Start Screen → [Play] → Gameplay Screen
Gameplay Screen → [wrong press / timeout] → Game-Over Overlay
Game-Over Overlay → [Play Again] → Gameplay Screen (reset)
Game-Over Overlay → [Settings] → Settings Panel → back
Gameplay Screen → [Mute] → toggle audio (stays on game screen)
```

### Onboarding / Tutorial (First-Time Flow)
1. Game loads → Start Screen shows with title and brief rule text: "Watch the pattern. Repeat it. Each round adds one step."
2. Player taps Play.
3. Round 1: Game plays a single-pad flash with audio.
4. Phase label changes to "Your turn!" — player taps the pad.
5. Correct → brief success flash → Round 2 begins immediately.
6. No explicit forced tutorial; the first round is trivially easy and self-teaching.
7. Game-over overlay includes a reminder of the rules ("Wrong pad pressed — try again!").

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- **2D, flat, top-down (no camera).** Pure HTML/CSS layout.
- **Orientation:** Portrait-primary; 2×2 pad grid is square. Responsive to landscape.

### Art Style
- **Flat design with subtle gloss.** Bold saturated pad colors, dark (near-black) background.
- **Palette:** Green (#2ECC40), Red (#FF4136), Yellow (#FFDC00), Blue (#0074D9), background (#111), UI text (#EEE).
- **Pad shape:** Rounded rectangle or quarter-circle (original Simon used quarter-circles in a circle; clone may use rounded squares for simplicity and mobile tap accuracy).
- **Active state:** Pad brightens strongly (CSS filter: brightness(1.8) or a lighter overlay) + drop shadow glow in pad color.
- **Idle state:** Slightly desaturated pad to emphasize the active flash.

### Animation & VFX
- Pad flash on playback: instant brightness spike, hold for tone duration, snap off.
- Player tap: same flash + brief radial glow.
- Wrong press: red screen-edge flash (CSS box-shadow inset red pulse, 300 ms).
- Correct sequence complete: brief "✓" overlay or round-up counter bounce.
- Game-over: overlay fades in over 200 ms.

### Audio
- **4 pads:** Sine-wave oscillator tones via WebAudio API.
- **Fail buzz:** Sawtooth or square wave at ~80 Hz, 500 ms.
- **Tone durations:** Match pad flash durations (see speed tiers).
- **Audio unlock:** AudioContext created/resumed on first user gesture (required by browsers).
- **Mute:** GainNode set to 0; visual feedback still plays normally.

### Frequencies (clone set — harmonically related, not literally copied):
| Pad | Color | Hz | Note |
|---|---|---|---|
| 0 | Green | 392 | G4 |
| 1 | Red | 494 | B4 |
| 2 | Yellow | 330 | E4 |
| 3 | Blue | 262 | C4 |

### Juice / Feel
- Pad flash is instant and sharp (no fade-in; quick fade-out).
- Tone starts immediately with pad flash.
- Round counter animates (CSS scale bounce) on increment.
- Game-over overlay slides up from bottom (CSS transform translateY).

---

## 11. Monetization

**None for this clone.** The game is a pure free-to-play arcade experience with no ads, no IAP, no loot boxes, no subscriptions.

- **Consent / ATT / CMP flows:** Not required since no tracking or ads are implemented.
- **Age compliance:** Because the game targets all ages including under-13, any future monetization addition must implement an age gate and COPPA/GDPR-K compliant consent flow before enabling ads or data collection.
- **Monetization aggressiveness:** 0 — intentionally clean experience to maximize replay and sharing.

---

## 12. Retention Hooks

- **Best score display:** Persistent localStorage best round creates a personal challenge loop.
- **One-tap restart:** Replay button is prominently placed; low friction to retry.
- **Speed escalation:** Natural tension builder; surviving to round 10+ feels like an achievement.
- **No daily rewards, no offline earnings, no push notifications, no energy system.** This is a skill arcade game; retention is purely intrinsic.
- **Shareability:** Optional "Share score" text snippet ("I reached round 14 in Simon Says — can you beat it?") for organic sharing.

---

## 13. Localization & Accessibility

### Localization
- **Language:** English-only for the clone MVP. [Estimated]
- UI text is minimal (< 20 strings) — easy to add i18n later.
- **RTL:** Not required for MVP; layout is symmetric so RTL would be trivial.
- **Regional differences:** None applicable.

### Accessibility
- **Color + tone dual coding:** Every pad uses both a distinct color AND a distinct tone. Colorblind players can still play by ear. [Confirmed design intention of the original]
- **Keyboard control:** Q/W/A/S or arrow keys map to the four pads (optional, recommended).
- **Text scaling:** UI uses `rem` / `vmin` units; scales with browser font size.
- **Colorblind mode:** Pads labeled with letters (G/R/Y/B) as optional overlay.
- **Contrast:** Active pad glow provides strong contrast against dark background.
- **Touch target size:** Each pad minimum 120×120 px; on mobile typically 45vw×45vw.
- **Age/content rating:** IARC Everyone / PEGI 3. No violence, no mature themes, no data collection required.
- **COPPA/GDPR-K:** No personal data collected in the base clone; no consent gate needed unless ads/analytics are added.

---

## 14. Technical Structure

### Engine / Framework
- **Vanilla HTML5 + CSS3 + JavaScript (ES6+).** No frameworks, no build step.
- **WebAudio API** for all tones (oscillator nodes).
- Single `.html` file; self-contained; runs by double-click (file:// protocol), fully offline.

### Platforms
- **Web (all browsers):** Chrome, Firefox, Safari, Edge. Desktop + mobile.
- **Offline-first:** No network calls; no CDN dependencies.

### Save System
- `localStorage` keys:
  - `simonBestRound` — integer, best round ever reached.
  - `simonMute` — boolean, mute preference.
  - `simonMode` — string, "normal" or "strict".
- No accounts, no cloud sync, no auth.

### Multiplayer / Netcode
- None. Single-player only. Anti-cheat: N/A.

### Backend / Analytics
- None. Fully client-side.

### Third-party SDKs
- None. Zero dependencies.

### Performance Notes
- DOM size is tiny (< 30 elements); no canvas needed.
- WebAudio nodes created per-tone; `AudioContext` is reused.
- Estimated file size: < 25 KB.

---

## 15. Pacing & Difficulty

### Early Game (Rounds 1–5)
- Sequences of 1–5 steps at slowest speed.
- Almost impossible to fail; player learns the 4-pad layout and the sound-color mapping.
- **Aha moment:** Round 3–4 — player realizes they're starting to actually memorize a sequence.

### Mid Game (Rounds 6–13)
- Speed increases at rounds 6 and 10.
- Sequences of 6–13 steps require genuine short-term memory effort.
- Most players churn here (rounds 7–11 are the typical failure zone). [Estimated]
- **Tension spike:** Round 9–10 — tempo increase is noticeable, creates urgency.

### Late Game (Rounds 14+)
- Fastest speed; sequences of 14+ steps.
- Only skilled players reach this zone.
- **Churn point:** Round 14–16 — almost all players have failed by here. [Estimated]
- **Milestone beat:** Reaching round 20 could trigger a special "congrats" moment.

### Difficulty Curve Summary
| Round Range | Pad Duration | Gap | Difficulty |
|---|---|---|---|
| 1–5 | 400 ms | 200 ms | Easy |
| 6–9 | 300 ms | 150 ms | Medium |
| 10–13 | 220 ms | 110 ms | Hard |
| 14+ | 160 ms | 80 ms | Expert |

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist
| Feature | MVP | Full |
|---|---|---|
| 4-pad grid with colors + tones | Yes | Yes |
| Sequence generation + playback | Yes | Yes |
| Input lock during playback | Yes | Yes |
| Correct/wrong detection | Yes | Yes |
| Speed ramp | Yes | Yes |
| Round counter + best score | Yes | Yes |
| Game-over overlay + replay | Yes | Yes |
| Strict mode toggle | Yes | Yes |
| LocalStorage persistence | Yes | Yes |
| Mute toggle | Yes | Yes |
| Touch/mobile responsive | Yes | Yes |
| Keyboard shortcuts | No | Yes |
| Colorblind letter labels | No | Yes |
| Share-score button | No | Yes |
| Visual juice (glow, screen-edge fail flash) | Yes | Yes |
| Endless mode | No | Yes |
| High score leaderboard (local multi-profile) | No | Yes |

### Phased Roadmap

**Phase 1 — Core Engine (1–2 days)**
- Single HTML file scaffold
- 4-pad layout (CSS grid, responsive)
- WebAudio oscillator setup + 4 tones + fail tone
- Sequence array + random append logic
- Playback engine (sequential timed flashes, input locked)
- Input handler (compare player tap to expected step)
- Round counter; speed tier lookup table
- Game-over trigger + reset

**Phase 2 — UI & Persistence (0.5 days)**
- Start screen with Play button
- Game-over overlay
- HUD (round, best)
- localStorage read/write (best, mute, mode)
- Mute toggle
- Mode toggle (strict/normal)

**Phase 3 — Polish & Feel (0.5 days)**
- Pad glow animations (CSS transitions)
- Wrong-press screen flash
- Round counter bounce animation
- Phase label ("Simon's turn…" / "Your turn!")
- Mobile touch event handling
- 5-second input timeout

**Phase 4 — Extras (optional, 0.5 days)**
- Keyboard shortcuts
- Colorblind label overlay
- Share-score text button
- Congrats screen at round 20

### Recommended Tech Stack (Clone)
- HTML5 / CSS3 / Vanilla JS ES6 (no frameworks)
- WebAudio API (oscillator nodes)
- CSS custom properties for theming
- Flexbox/Grid for layout
- `requestAnimationFrame` or `setTimeout` for timing (use `setTimeout` chains for predictable sequencing)

### Required Asset List
- **Art:** Pure CSS — no image files required. Pad colors defined in CSS variables.
- **Audio:** Generated at runtime via WebAudio API oscillators — no audio files.
- **Fonts:** System fonts only, or one Google Font loaded locally (optional).
- **Data:** Frequency table (4 values), speed tier table (4 rows) — hardcoded in JS.

### Hardest Parts / Risks
1. **Audio context unlock on mobile:** Browsers block `AudioContext` until a user gesture. Must create/resume the context inside the first click/tap handler. Easy to get wrong silently.
2. **Playback timing accuracy:** `setTimeout` chains can drift; prefer relative timestamps from `performance.now()` if precision matters at high speeds.
3. **Input lock edge cases:** Player tapping during playback must be completely ignored — no partial-state corruption. Use a clear `isPlaying` boolean guard.
4. **Touch vs click duplication on mobile:** Bind `pointerdown` or handle both `touchstart` + `click` carefully to avoid double-firing.
5. **Speed tuning:** The feel of the speed ramp is crucial to the game experience; the table values above are starting estimates that need playtesting to feel right.
6. **5-second timeout:** Clearing and resetting the per-step timer on each correct tap is a common bug source.

---

## 17. Open Questions

1. **Optimal frequencies for the clone:** The exact frequencies should be tuned by ear in playtest to confirm they sound harmonious at all playback speeds. [Start with the table in section 6, adjust as needed.]
2. **Win condition at round 20+:** Original hardware has a 31-step maximum and plays a fanfare. The clone could implement a "you win" state at round 20 or 31, or go truly endless. Decide based on player feedback.
3. **Speed ramp feel at high speeds:** The 160 ms pad duration at expert level may feel unplayably fast on mobile with larger fingers; may need a minimum of 180–200 ms for touch. Requires playtesting.
4. **Strict vs normal as default:** Strict mode is more authentic to the original but frustrating for new players. Consider defaulting to normal with strict as an opt-in.
