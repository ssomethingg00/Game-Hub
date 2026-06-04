# Memory Card Match — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not any original name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Memory Card Match is a classic concentration/memory puzzle game where all cards begin face-down in a grid; the player flips two cards per turn seeking matching pairs, memorizing card positions to clear the board efficiently. It is a pure, single-player casual puzzle that rewards spatial memory and pattern recognition. The genre is **casual puzzle / memory / brain-training**; comparable titles include Concentration (the original board game), Simon Says (memory sequencing), Mahjong Solitaire (match-to-clear pattern), and Lumosity Memory Matrix.

**Quick facts:**
- Developer: Original concept public-domain; digital variants by hundreds of studios.
- Platform target: Web (HTML5), iOS, Android.
- Release: This clone built 2026.
- Age/content rating: IARC 3+ (Everyone) — no violence, no mature content. [Estimated]
- Monetization model: Free-to-play, optional rewarded ads and cosmetic IAP. [Estimated]
- Session length: 1–5 minutes per round.
- Play style: Active, single-player, portrait or landscape.

---

## 2. Core Loops

- **30-second loop:** Tap a face-down card to flip it; tap a second face-down card; watch reveal — if symbols match, both stay face-up (pair cleared); if no match, both flip back face-down after ~1 s delay. Repeat.
- **Session loop:** Choose difficulty (4×4 / 4×6 / 6×6); play until all pairs cleared; receive star rating (1–3) based on move efficiency; optionally replay to beat personal best (fewest moves, fastest time).
- **Meta loop:** Improve star ratings on each difficulty tier; unlock visual themes (card backs, emoji sets); compare personal best records stored locally; pursue a "perfect" clear with minimum possible moves.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics & Rules
- Board is a rectangular grid of N×M cards, always even count (N×M must be divisible by 2). [Confirmed — classic rule]
- Each pair of identical symbols appears exactly twice, randomly shuffled across the grid. [Confirmed]
- A "turn" = flipping exactly two cards. No more than two cards can be face-up and unmatched at once.
- **Match:** Both face-up cards share the same symbol → they are permanently locked face-up (matched state). Player earns +1 pair. No move penalty.
- **No match:** Both face-up cards differ → they are flipped back face-down after a 1.0 s reveal delay. Player loses no lives but +1 to move counter.
- **Input lock:** From the moment the second card is clicked until the compare delay resolves (~1.0 s), all card-click input is disabled to prevent a "third card" cheat.
- **Win condition:** All pairs matched. Win screen with stats is displayed.
- **No lose/fail state:** The game cannot be "lost" — it is a race for efficiency and speed, not survival. [Confirmed — standard memory game design]
- **Move counter:** Increments by 1 each time two cards are flipped (regardless of match). [Confirmed]
- **Timer:** Counts up from 0:00 from first card flip; stops when last pair matched.

### Game Modes
| Mode | Description |
|------|-------------|
| Easy | 4×4 grid (8 pairs, 16 cards) |
| Medium | 4×6 grid (12 pairs, 24 cards) |
| Hard | 6×6 grid (18 pairs, 36 cards) |

All modes share identical rules; difficulty scales only via grid size. [Confirmed — universal design]

### Controls
- **Desktop:** Left-click on a face-down card to flip it.
- **Mobile:** Tap on a face-down card to flip it.
- **Prevented actions:** Clicking an already-flipped (unmatched, currently face-up) card, clicking a matched (permanently face-up) card, clicking any card while compare-delay timer is running.
- **Orientation:** Portrait-first but fully responsive; grid reflows for landscape.

### Feedback Systems
- **Flip animation:** CSS 3D rotateY(180deg) transition (~0.35 s) reveals card face.
- **Match feedback:** Matched pair briefly pulses/glows green, then settles to a distinct "matched" visual state.
- **No-match feedback:** Cards briefly shake or flash red before flipping back.
- **Win feedback:** All cards briefly cascade-flip, confetti/sparkle overlay, modal with stats.
- **Audio:** WebAudio-generated SFX — flip click, match chime, mismatch thud, win fanfare. Mute toggle.
- **Haptic:** Not applicable for web; optional for native ports.

### AI / Opponent
None — single-player only. Difficulty is structural (grid size), not AI-driven. [Confirmed]

---

## 4. Progression

### Difficulty Unlock
- All three difficulty levels available from the start. No unlock gating. [Estimated — suitable for casual web]
- Best scores (moves + time) stored per difficulty in localStorage.

### Star Rating System
Stars awarded at session end based on move efficiency:

| Stars | Condition |
|-------|-----------|
| ⭐⭐⭐ | Moves ≤ pairs × 1.5 (near-perfect memory) |
| ⭐⭐ | Moves ≤ pairs × 2.5 (good performance) |
| ⭐ | Moves > pairs × 2.5 (any completion) |

*Example (Easy, 8 pairs): 3 stars ≤ 12 moves; 2 stars ≤ 20 moves; 1 star > 20 moves.* [Estimated]

### Cosmetic Progression (Optional Extension)
- Emoji card sets: unlock new symbol sets (animals, food, space, sports) by completing each difficulty. [Estimated]
- Card back themes: unlock via replaying. [Estimated]

### Prestige / Reset
None in core loop. "New Game" resets board and timer only.

---

## 5. Economy & RNG  *(tables)*

### Currencies
| Currency | Type | Earned From | Spent On |
|----------|------|-------------|----------|
| Stars | Soft / cosmetic | Completing rounds efficiently | Unlocking card themes (optional) |
| Coins (optional) | Soft | Win bonus, daily play | Hint reveal (optional IAP feature) |

*The core game has no required economy — progression is purely score-based.* [Confirmed — standard for puzzle/memory genre]

### Cost Scaling
No upgrade cost scaling exists in the core game loop. If cosmetic unlocks are added:
- Theme unlock cost: flat 10 stars each [Estimated]
- No exponential scaling needed.

### RNG
| Element | Mechanic | Notes |
|---------|----------|-------|
| Card shuffle | Fisher-Yates shuffle on pair array | True random, seeded by Date.now() |
| Card position | Uniformly random across all grid cells | No weighting |
| Symbol set | Fixed 18-symbol pool; Easy uses first 8, Medium 12, Hard all 18 | [Estimated] |

No gacha, no drop rates, no loot boxes. The only randomness is the initial shuffle. [Confirmed — genre standard]

### Income / Pacing
- Each round is self-contained; no persistent income stream.
- Optimal (theoretical minimum) moves = number of pairs (if player had perfect memory from turn 1).
- Typical first-play move count: Easy ~14–20, Medium ~22–36, Hard ~36–60. [Estimated from genre norms]

---

## 6. Content Inventory  *(counts + lists)*

### Grid Configurations
| Difficulty | Grid | Cards | Pairs |
|------------|------|-------|-------|
| Easy | 4×4 | 16 | 8 |
| Medium | 4×6 | 24 | 12 |
| Hard | 6×6 | 36 | 18 |

### Symbol Sets (Emoji-based, no external assets required)
**Core set (18 symbols):** 🐶 🐱 🐭 🐹 🐰 🦊 🐻 🐼 🐨 🐯 🦁 🐮 🐷 🐸 🐵 🐔 🐧 🐦

**Optional alternate sets:**
- Food: 🍎 🍊 🍋 🍇 🍓 🍒 🍑 🍍 🥝 🍆 🥕 🌽 🍕 🍔 🌮 🍣 🍦 🎂
- Space: 🚀 🌙 ⭐ 🪐 ☄️ 🌍 🔭 👾 🛸 🌠 💫 🌌 🪨 🌑 🛰️ 🌟 💥 🔬
- Sports: ⚽ 🏀 🎾 🏐 ⚾ 🏈 🎱 🏓 🥊 ⛷️ 🏋️ 🤸 🎯 🏹 🎲 🃏 🎮 🏆

### Card Back Design
Single consistent back face (decorative CSS pattern — no external image). [Confirmed — offline requirement]

### Screens
1. Title/Difficulty Select
2. Game Board
3. Win Overlay
4. Settings Modal

No levels, no characters, no enemies. Content is purely the symbol grid. [Confirmed]

---

## 7. Theme, Narrative & Tone

- **Setting:** Abstract / playful — no specific world or story.
- **Premise:** No narrative. Pure puzzle challenge. [Confirmed — genre standard]
- **Story delivery:** None. Game is mechanical; win screen shows stats only.
- **Character personalities:** None.
- **Writing style:** Minimal copy — button labels, HUD labels, win message ("Well done! You matched all X pairs in Y moves and Z seconds.").
- **Tone:** Cheerful, colorful, light, rewarding. Suitable for all ages.
- **IP:** No licensed IP; original emoji-based symbols are not IP-owned. [Confirmed]
- **Color palette:**
  - Card back: Deep navy #1a237e with subtle pattern
  - Card face: Warm white #fafafa with emoji centred
  - Matched state: Soft green tint #e8f5e9 border
  - Background: Gradient — deep purple-navy to mid-blue
  - Accent: Gold #ffd700 (stars), Coral #ff6b6b (mismatch flash)

---

## 8. Meta & Social Systems

### Missions / Achievements
- "First Match" — match your first pair. [Estimated]
- "Speed Demon" — complete Easy in under 60 seconds. [Estimated]
- "Perfect Memory" — complete any difficulty in exactly (pairs) moves. [Estimated]

### Daily / Streak Features
- No daily reward system in core MVP. [Estimated — out of scope for single-session web game]
- Optional: "Daily Puzzle" — pre-seeded shuffle of the day shared via URL hash. [Estimated extension]

### Live Ops
- No live-ops in web single-player version. [Estimated — not applicable]
- Extension: weekly "theme of the week" via URL param.

### Leaderboards / Social
- Local personal best only (localStorage). [Confirmed for MVP]
- Optional extension: global leaderboard via lightweight backend (Supabase/Firebase).
- Share button: generates a text summary "I cleared Hard in 42 moves and 2m 15s — can you beat me?" with Web Share API. [Estimated extension]

### Multiplayer
None in core game. Optional extension: hot-seat 2-player alternating turns mode. [Estimated]

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|--------|---------|
| Splash/Title | Logo, tagline, difficulty selector (Easy/Medium/Hard buttons), New Game CTA |
| Game Board | Active grid + HUD (moves, timer, best score, mute, restart) |
| Win Overlay | Modal over board — star rating, stats (moves/time/best), Replay + Menu buttons |
| Settings Modal | Mute toggle, card back theme selector, about/credits |

### Settings Menu Contents
- Sound On/Off toggle
- Card theme selector (Animals / Food / Space / Sports)
- About / Version info

### Gameplay HUD Elements
```
[ 🐾 Memory Match ]   [ 🔇 ]  [ ↺ ]
  Moves: 12    Time: 0:45    Best: 10 moves
[ ======= CARD GRID ======= ]
```
- Top bar: game title (left), mute icon (right), restart icon (right)
- Info bar: moves count, live timer, best moves for current difficulty
- Grid: responsive, centered, fills available viewport

### Navigation Flow
```
Title Screen
  └─► [Select Difficulty + New Game] → Game Board
                                            └─► [All pairs matched] → Win Overlay
                                                      ├─► [Replay] → Game Board (same difficulty)
                                                      └─► [Menu] → Title Screen
                                        ├─► [⚙ Settings] → Settings Modal (overlay)
                                        └─► [↺ Restart] → Game Board (same difficulty, new shuffle)
```

### Onboarding / Tutorial (First-Time User Flow)
1. App opens → Title screen shown with difficulty cards (Easy recommended, highlighted).
2. First launch: a brief tooltip "Tap two cards to find matching pairs!" appears over the grid for 3 seconds.
3. First card flip: no forced tutorial — game is self-explanatory by genre conventions.
4. First match: brief "+1 Match!" toast floats up from the matched pair.
5. First mismatch: cards visually shake before flipping back — teaches the mechanic implicitly.
6. No forced tutorial steps — discovery-based onboarding sufficient for this genre. [Confirmed — memory games require no complex tutorial]

### Popups / Modals
- **Win Modal:** Star rating (animated), moves taken, time taken, best record (updated live), "New Game" and "Change Difficulty" buttons.
- **Restart Confirm:** Optional "Are you sure?" only if mid-game (>0 moves made and game not won). [Estimated]

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- 2D flat; no camera — static top-down grid layout. [Confirmed]
- Portrait-primary; responsive for landscape.
- Aspect: fluid — fills any viewport.

### Art Style
- Flat / Material-adjacent design with subtle depth (box-shadows, border-radius).
- Card faces: large emoji centered on rounded rectangle, light drop-shadow.
- Card backs: solid deep-navy with a repeating CSS diamond/cross pattern for texture.
- Grid background: deep gradient (navy → indigo).
- Smooth, rounded corners throughout; no sharp pixel art.

### Color Palette
| Role | Color | Hex |
|------|-------|-----|
| Background gradient start | Deep navy | #0d1b2a |
| Background gradient end | Indigo | #1a237e |
| Card back | Navy | #1565c0 |
| Card face | Off-white | #fafafa |
| Matched card | Mint green | #a5d6a7 border |
| Mismatch flash | Coral red | #ef5350 |
| HUD text | White | #ffffff |
| Star (filled) | Gold | #ffd700 |
| Star (empty) | Grey | #9e9e9e |
| Accent / button | Amber | #ffb300 |

### Animations & VFX
- **Card flip:** CSS perspective + rotateY(180deg) in 0.35 s ease-in-out. [Confirmed — standard CSS 3D]
- **Match pulse:** brief scale(1.1) + green glow, duration 0.3 s. [Estimated]
- **Mismatch shake:** translateX(-5px 5px) oscillation × 3, duration 0.4 s, then flip-back. [Estimated]
- **Win cascade:** cards flip face-down then face-up sequentially with staggered delay.
- **Confetti:** CSS keyframe particle burst on win (no canvas needed). [Estimated]
- **Star rating reveal:** stars "pop" into place one by one with bounce easing.

### Audio (WebAudio API — no files)
| Event | Sound | Notes |
|-------|-------|-------|
| Card flip | Short click (oscillator 440Hz, 0.05s decay) | [Estimated] |
| Match | Rising chime (C5→E5→G5, 0.3s) | [Estimated] |
| Mismatch | Low thud (80Hz sine, 0.2s decay) | [Estimated] |
| Win fanfare | Ascending arpeggio + sustain (C5-E5-G5-C6) | [Estimated] |
| Button hover | Subtle tick (1000Hz, 0.02s) | [Estimated] |

Mute toggle disables all audio output. State persisted in localStorage.

### "Juice" / Game Feel
- Every card flip has a tactile snap sound.
- Matched pairs subtly glow and are visually "deflated" from the attention pool.
- Unmatched cards snap back with a physical-feeling shake.
- Win screen feels celebratory (stars animate, fanfare plays, confetti).
- Grid has gentle entrance animation on load (cards fade in staggered).

---

## 11. Monetization

*This blueprint covers a free web game. Monetization is optional/extension only.*

### Ads (Optional Extension)
| Type | Placement | Frequency |
|------|-----------|-----------|
| Interstitial | Between games (after win, before new game) | Max 1 per 2 games [Estimated] |
| Rewarded | "Reveal a random pair" hint — user opts in | On-demand, player-triggered [Estimated] |
| Banner | Bottom of screen (non-intrusive) | Persistent while idle [Estimated] |

### IAP (Optional Extension)
| Product | Price | Contains |
|---------|-------|---------|
| Remove Ads | $1.99 | No ads ever [Estimated] |
| Card Theme Pack | $0.99 | All 4 emoji theme sets [Estimated] |
| Bundle | $2.49 | Remove Ads + All Themes [Estimated] |

### Loot Boxes / Gacha
None — no RNG monetization. [Confirmed for core game]

### Consent / ATT / CMP (if ads are added)
- iOS: App Tracking Transparency prompt before any ad SDK init. [Required by Apple policy]
- GDPR: CMP consent popup for EU users before ad personalization. [Required]
- Age gate: if advertising to U13, disable behavioral ads (COPPA compliance). [Required]
- For this web-only MVP (no external ad SDK): none of the above applies. [Confirmed]

### Aggressiveness
Very low for core MVP. Game is completable 100% free with no friction. [Estimated]

---

## 12. Retention Hooks

### Daily Rewards
None in MVP. [Estimated — out of scope for casual web puzzle]

### Offline / Idle Earnings
None — game requires active play; no idle mechanics. [Confirmed — not a genre feature]

### Push Notifications
Not applicable for web (no service worker notifications in MVP). [Estimated]

### FOMO / Urgency Mechanics
None in MVP. Timer creates internal urgency (player wants to beat their best time) but imposes no external pressure. [Confirmed]

### Energy / Lives System
None — unlimited plays, no energy gate. [Confirmed — genre standard for memory puzzle]

### Intrinsic Retention Hooks
- Personal best records encourage replay ("I can do better than 20 moves").
- Three-star system gives clear improvement target.
- Three difficulty tiers provide natural difficulty ladder.
- Completionism: collecting 3 stars on all three difficulties.
- Optional: "Daily Puzzle" seeded by date for consistent return. [Estimated extension]

---

## 13. Localization & Accessibility

### Supported Languages
MVP: English only. [Estimated]
Extension: Spanish, French, German, Japanese, Brazilian Portuguese (top web game locales).

### RTL Support
Not required for emoji-only symbol set. Text labels (minimal) would need RTL layout for Arabic/Hebrew. [Estimated]

### Text Scaling
- No body text during gameplay; HUD uses scalable rem units.
- Win modal text scales with viewport; minimum touch target 44×44 px per Apple HIG. [Confirmed standard]

### Colorblind Modes
- Emoji symbols are shape-differentiated, not color-dependent → naturally colorblind-accessible. [Confirmed]
- Matched state uses both green tint AND a checkmark icon (not color-only). [Estimated — best practice]

### Difficulty / Assist Options
- Three grid sizes serve as difficulty selector.
- No explicit "assist mode" in MVP; natural accessibility via Easy (smaller grid).
- Optional extension: "Hint" button reveals a random unmatched pair briefly.

### Age/Content Rating
- IARC 3+ / Everyone — appropriate for all ages including children. [Confirmed]
- No violence, sexual content, gambling, in-game purchases in core MVP.
- COPPA compliance: no data collection, no account creation, no behavioral ads in MVP. [Confirmed — localStorage only, no server]

### Regional Differences
None — no region-locked content, no regional pricing in web MVP. [Estimated]

---

## 14. Technical Structure

### Engine / Framework
- **Vanilla HTML5 + CSS3 + JavaScript (ES6+)** — zero dependencies, no build step. [Confirmed — per project requirement]
- Single `.html` file: inline `<style>` and `<script>` tags.
- No WebGL, no canvas — pure DOM + CSS transforms.

### Platforms
- **Primary:** Any modern web browser (Chrome, Firefox, Safari, Edge) — desktop and mobile.
- Works offline (no network resources).
- Installable as PWA with minimal manifest addition (optional extension).

### Online / Offline
Fully offline. No network requests. [Confirmed — per project requirement]

### Save System
- **localStorage** key-value pairs:
  - `mcm_best_easy` → `{moves: N, time: S}` JSON
  - `mcm_best_medium` → `{moves: N, time: S}` JSON
  - `mcm_best_hard` → `{moves: N, time: S}` JSON
  - `mcm_mute` → `"true"|"false"`
  - `mcm_theme` → `"animals"|"food"|"space"|"sports"`

### Accounts / Auth
None — anonymous local-only. [Confirmed]

### Cross-Device Sync
None in MVP. [Estimated]

### Multiplayer / Netcode
None. Single-player only. [Confirmed]

### Anti-Cheat
N/A — single-player, no server, no competitive stakes. [Confirmed]

### Backend Services
None. Zero backend. [Confirmed]

### Analytics
None in MVP. Optional: plausible.io snippet (privacy-respecting, no consent needed). [Estimated]

### Third-Party SDKs
None in MVP. [Confirmed]

### App Size
Single `.html` file ~25–50 KB (unminified). [Estimated]

### Performance Notes
- Target 60fps on mid-range mobile (CSS transitions offloaded to GPU via translateZ hack).
- Grid reflow handled via CSS Grid `auto-fill` / `minmax`.
- No memory leaks: event listeners on card elements; cleared on New Game via `innerHTML` replacement.

---

## 15. Pacing & Difficulty

### Early Game (Easy — 4×4)
- 16 cards, 8 pairs — manageable for any player.
- Most players solve in 14–24 moves.
- "Aha" moment: first accidental match where memory kicked in. [Estimated]
- Session length: ~1–2 minutes.

### Mid Game (Medium — 4×6)
- 24 cards, 12 pairs — requires deliberate memory strategy.
- Players start using spatial chunking (remember quadrant, not exact position).
- Most players: 20–40 moves.
- Session length: ~2–4 minutes.
- Churn risk: players who dislike the larger grid switch back to Easy. [Estimated]

### Late Game (Hard — 6×6)
- 36 cards, 18 pairs — significant working memory load.
- Strategy matters: players who study cards before flipping perform significantly better.
- Most players: 35–70 moves.
- Session length: ~3–7 minutes.
- Achievement hook: completing Hard feels meaningfully different from Easy. [Estimated]

### Difficulty Curve
```
Easy → Medium → Hard
  └─ exponential grid growth: 16 → 24 → 36 cards
  └─ cognitive load scales non-linearly with grid size
  └─ optimal moves: 8 → 12 → 18 (theoretical minimum)
```

### Key Milestone Moments
1. **First match** — immediate positive reinforcement.
2. **Half-board cleared** — visible progress milestone.
3. **Last pair** — tension and relief.
4. **Personal best beaten** — strongest replay hook.
5. **3-star first-time achievement** — mastery signal.

### Churn Points
- Hard mode for casual players (grid too large → frustration). [Estimated]
- Repetitive symbol set after many plays (addressed by theme unlocks). [Estimated]
- Lack of daily content (addressed by Daily Puzzle extension). [Estimated]

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---------|-----|------|
| 3 grid difficulties | ✓ | ✓ |
| Fisher-Yates shuffle | ✓ | ✓ |
| Flip animation (CSS 3D) | ✓ | ✓ |
| Match/mismatch logic | ✓ | ✓ |
| Input lock during compare | ✓ | ✓ |
| Move counter + timer | ✓ | ✓ |
| Win detection + overlay | ✓ | ✓ |
| Star rating system | ✓ | ✓ |
| Personal best (localStorage) | ✓ | ✓ |
| WebAudio SFX | ✓ | ✓ |
| Mute toggle | ✓ | ✓ |
| Mobile/touch support | ✓ | ✓ |
| Responsive grid | ✓ | ✓ |
| Multiple emoji theme sets | — | ✓ |
| Daily puzzle (seeded RNG) | — | ✓ |
| Share result (Web Share API) | — | ✓ |
| PWA / offline install | — | ✓ |
| Global leaderboard | — | ✓ |
| Achievements system | — | ✓ |
| Hint system (rewarded) | — | ✓ |
| 2-player hot-seat mode | — | ✓ |

### Phased Build Roadmap

**Phase 1 — Core Engine (Days 1–2)**
- Single HTML file scaffold with inline CSS/JS.
- Grid rendering function (parametric N×M).
- Fisher-Yates shuffle of pair array.
- Card flip state machine (idle → first-flipped → second-flipped → compare → resolved).
- Match detection logic.
- Input lock during compare delay.

**Phase 2 — Game Loop (Days 2–3)**
- Move counter + timer (setInterval, startable/stoppable).
- Win detection (all pairs matched check after each compare).
- Win overlay with stats.
- New Game + Restart functionality.
- Difficulty selector (rebuilds grid).

**Phase 3 — Polish & Feel (Days 3–4)**
- CSS 3D flip animation.
- Match pulse / mismatch shake animations.
- WebAudio SFX (flip, match, mismatch, win).
- Mute toggle.
- Star rating calculation and animated display.
- Responsive CSS Grid layout.

**Phase 4 — Persistence & Accessibility (Day 4)**
- localStorage best score save/load/display.
- Mobile tap targets (min 44px).
- Viewport meta tag.
- Colorblind-safe design audit.
- Win cascade animation.
- Cross-browser testing.

**Phase 5 — Extensions (Post-MVP)**
- Alternate emoji theme sets.
- Daily puzzle mode (seeded RNG from date).
- Share result button.
- PWA manifest + service worker.

### Recommended Tech Stack
- **HTML5** — semantic structure.
- **CSS3** — Grid layout, custom properties (variables), keyframe animations, 3D transforms.
- **Vanilla ES6+ JavaScript** — no frameworks, no dependencies.
- **Web Audio API** — programmatic SFX, no audio files.
- **localStorage API** — persistence.

### Required Asset List
- All assets generated in-code (emoji, CSS shapes, WebAudio).
- No external images, fonts, audio files, or CDN resources. [Confirmed]

### Hardest Parts / Biggest Risks
1. **Input lock correctness** — preventing a third card flip mid-compare; must disable clicks cleanly and re-enable after delay. Common source of bugs.
2. **Double-flip guard** — clicking the same card twice should not count as a pair; must check card identity.
3. **Compare timer cleanup** — if player hits Restart during a compare delay, the pending timeout must be cleared and state reset.
4. **Win detection edge case** — must fire *after* the match animation, not before, so the win overlay doesn't appear before cards visually settle.
5. **Grid responsiveness** — 6×6 grid on a 320px-wide phone needs cards ~44px each minimum; CSS `minmax` + `fr` units handle this but needs careful sizing.
6. **iOS Safari 3D transform** — requires `-webkit-backface-visibility: hidden` for Safari compatibility.
7. **Timer drift** — using `setInterval` for display; actual elapsed time should be computed from `Date.now()` delta, not interval count, to prevent drift.

---

## 17. Open Questions

1. **Target emoji set finalization** — the 18-symbol animal set is solid; Food/Space/Sports sets are estimated and need final curation to ensure distinct visual silhouettes on small cards. [Needs playtest]
2. **Mismatch delay duration** — 1.0 s is standard; some implementations use 0.8 s or 1.2 s. Optimal value depends on player age/target audience. [Needs A/B test]
3. **Star thresholds** — the ×1.5 / ×2.5 multipliers are estimated from genre norms; actual fun requires playtesting with real users on each difficulty. [Needs playtest]
4. **Mobile grid card sizing** — exact `minmax` values for the 6×6 Hard grid on 320px viewport need real-device testing to confirm 44px minimum tap target. [Needs device test]

---

*Blueprint complete. All 17 sections filled. Economy tables provided. RNG documented as Fisher-Yates shuffle only (no gacha). Win/lose states defined. Screen map and onboarding flow written. Monetization noted as optional extension. Retention hooks addressed (no idle/offline). Accessibility and age rating confirmed. Build plan with phased roadmap and flagged risks complete.*
