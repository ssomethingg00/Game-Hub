# Memory Flip — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Memory Flip is a single-player playing-card concentration game. Players reveal a grid of face-down cards two at a time, seeking matching pairs by rank and color. The game is a modernized digital take on the classic "Concentration" card game, distinguished from generic emoji memory games by using a real 52-card deck (standard suits ♠ ♥ ♦ ♣, red/black color groupings, rank pips). Core appeal is the satisfaction of board clearing combined with the competitive pull of beating your own move count and time record.

**Quick facts:**
- Genre: Card game / Memory / Puzzle (Solitaire)
- Developer/Reference: Classic public-domain card game ("Concentration"), digitized
- Platforms: Web (HTML5), any modern browser, offline-capable
- Age/Content Rating: ESRB E (Everyone) / PEGI 3 — suitable for all ages [Confirmed]
- Monetization model: None (free, no ads, no IAP in base clone spec)
- Session length: 2–8 minutes depending on difficulty [Estimated]
- Play style: Active single-player, turn-based card reveals

---

## 2. Core Loops

- **30-second loop:** Click a face-down card → it flips face-up with a 3D CSS animation → click a second face-down card → cards are compared → match: both stay face-up and animate a "match glow" → no match: both flip back face-down after a 1-second reveal pause.
- **Session loop:** Choose difficulty (Easy/Medium/Hard grid size) → cards are shuffled and laid face-down → repeat the 30-second loop until all pairs are found → win screen shows total moves, elapsed time, and a 1–3 star rating → player can restart or change difficulty.
- **Meta loop:** Best score (fewest moves and fastest time) is saved to localStorage per difficulty → player returns to beat their personal best → optionally unlocks star ratings (1-star, 2-star, 3-star) for each difficulty level.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics & Rules
- A deck of pairs is generated from a standard 52-card deck subset: a pair = two cards of the same rank AND same color (e.g., 7♥ + 7♦ = match; 7♥ + 7♠ = no match). [Confirmed — standard Concentration rules; color-match variant]
- Cards are shuffled and placed face-down in a grid.
- Player clicks any face-down card — it flips to reveal its rank+suit.
- Player clicks a second face-down card — it also flips.
- **Match:** Both cards stay face-up, a match animation plays, move counter increments.
- **No match:** Both cards are visible for ~1 second, then flip back face-down. Move counter still increments.
- Input is locked during the compare phase (no third card can be flipped while two non-matched cards are being shown).
- Win condition: All pairs found (all cards face-up).
- No lose condition — this is a score-optimization puzzle, not a lives-based game.

### Game States
1. **IDLE** — Title/difficulty screen, no game in progress.
2. **PLAYING** — Active game: cards dealt, timer running, player can click.
3. **COMPARE** — Two cards revealed; input locked; comparing cards.
4. **WIN** — All pairs matched; overlay shows result.
5. **PAUSED** (optional) — Timer paused via menu.

### Controls
- **Click / Tap** on any face-down card to flip it.
- Click **New Game** button to reset and re-shuffle.
- Click **Difficulty** selector (Easy / Medium / Hard) — rebuilds the grid.
- Click **Mute** toggle — silences WebAudio SFX.
- Touch-friendly: all targets ≥ 44×44 px on mobile.

### Input Guards
- Cannot flip an already-face-up (matched) card.
- Cannot flip the same card twice in one turn.
- Cannot flip a third card while two unmatched cards are in the compare phase.

### Difficulty Modes

| Difficulty | Grid    | Cards | Pairs |
|------------|---------|-------|-------|
| Easy       | 4 × 3   | 12    | 6     |
| Medium     | 4 × 4   | 16    | 8     |
| Hard       | 6 × 6   | 36    | 18    |

### AI / Opponent
None — single-player only. [Confirmed]

### Feedback Systems
- **Visual:** 3D CSS rotateY card flip (0.4s ease); matched cards glow gold; mismatched cards briefly shake/dim before flipping back; win overlay with star animation.
- **Audio:** Flip SFX (short click), match SFX (chime), mismatch SFX (soft thud), win fanfare — all WebAudio-generated (no external files).
- **Haptic:** Not implemented in web version (device haptics not available without native wrapper).

---

## 4. Progression

### Within a Game
- No leveling or upgrade tree — progression is implicit: fewer face-down cards remain as pairs are matched, which makes the board less daunting and aids memory.

### Across Sessions
- **Stars per difficulty** — 1/2/3 stars based on move efficiency:
  - Easy: ≤12 moves = 3★, ≤18 = 2★, else 1★ [Estimated]
  - Medium: ≤16 moves = 3★, ≤24 = 2★, else 1★ [Estimated]
  - Hard: ≤40 moves = 3★, ≤56 = 2★, else 1★ [Estimated]
- **Personal Best** — best (fewest moves + fastest time) per difficulty stored in localStorage.
- No unlockables, no tech tree, no prestige — the loop is pure score improvement.

### Gating
- Hard mode is accessible from the start — no unlock requirement. [Estimated — common in casual puzzle games]

---

## 5. Economy & RNG *(tables)*

### Currencies

| Currency | Type  | Earned From | Spent On |
|----------|-------|-------------|----------|
| Stars    | Score | Completing a board with few moves | Displayed as personal achievement only — no spend |
| Moves    | Score | Flipping two cards (each pair of flips = 1 move) | N/A |
| Time (seconds) | Score | Elapsed play time | N/A |

*No soft/hard in-game currency — this is a free score-based game with no economy.*

### Cost-Scaling
- None. No upgrades, no economy to scale.

### RNG
- **Shuffle:** Fisher-Yates shuffle applied once to the full pair array at game start. [Confirmed — standard algorithm]
- **Drop rates:** N/A (no gacha, no loot boxes).
- **Outcome:** All pairs are guaranteed to be present; shuffle is uniform random. Card face-up probability per position = 1 / remaining_cards (pure shuffle). [Confirmed]

---

## 6. Content Inventory *(counts + lists)*

### Card Deck Used
- Source deck: Standard 52 cards (4 suits × 13 ranks).
- Ranks: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K.
- Suits: ♠ (black), ♣ (black), ♥ (red), ♦ (red).
- Pair definition: Same rank + same color = a pair. So:
  - A♠ + A♣ = pair (both black Aces)
  - A♥ + A♦ = pair (both red Aces)
  - Total unique pairs available: 26 (13 ranks × 2 color groups)

### Grid Content per Difficulty
- Easy (6 pairs): Random selection of 6 of 26 available pairs = 12 cards.
- Medium (8 pairs): Random selection of 8 pairs = 16 cards.
- Hard (18 pairs): Random selection of 18 pairs = 36 cards.

### Game Modes
- 1 mode: Singleplayer concentration. [Confirmed]

### Screens
- 1 main game screen with difficulty selector, HUD, card grid, win overlay.

### Cosmetics / Collections
- None in base version. [Estimated — could add card back themes as cosmetics]

---

## 7. Theme, Narrative & Tone

### Setting & Visual Theme
- Casino card table aesthetic: deep green felt background, cream/white card faces, red and black suit colors, gold accents for matches and win state.
- No narrative or story — the game is purely mechanical.

### Premise
- No story or characters. The framing is implicit: you are a card sharp practicing memory at the casino table.

### Tone
- Calm, focused, satisfying. Slightly luxurious (casino palette). Not tense or stressful — the lack of a lose condition keeps it relaxed.

### Writing Style
- Minimal UI text. Labels are clear and short ("New Game", "Moves", "Best", "You Win!"). No dialogue.

### Licensed IP
- None. Uses public-domain playing card symbols (Unicode suits). [Confirmed]

---

## 8. Meta & Social Systems

### Missions / Achievements
- No formal mission system. Stars per difficulty act as informal achievement targets. [Estimated]

### Events / Seasons
- None — the game is a single static experience. No live-ops. [Confirmed for base version]

### Leaderboards
- None in base version; personal best only (localStorage). [Estimated]

### Multiplayer / Social
- None — single-player only. [Confirmed]

### Sharing
- Win screen text ("I cleared Hard in 42 moves!") could be copy-pasted; no built-in share button in base spec. [Estimated]

### Live-Ops Cadence
- None required — purely single-player offline game. No ongoing content burden.

---

## 9. UI / UX & Screen Map

### Screen List

| Screen | Purpose |
|--------|---------|
| Main Game Screen | Single screen housing: difficulty selector, HUD, card grid, New Game button, mute toggle |
| Win Overlay | Full-screen overlay on board clear: shows stars, moves, time, best score comparison, Play Again button |
| How-To Tooltip / Panel | Small "?" button reveals brief rules panel (inline, not a separate screen) |

### Settings / Options
- **Mute toggle** — silences all WebAudio SFX.
- No language, account, or other settings in base version.

### HUD Elements (during gameplay)
- Moves counter (label + number, top-left)
- Timer — elapsed time MM:SS (top-center)
- Best score display (top-right: best moves / best time for current difficulty)
- Difficulty selector (Easy / Medium / Hard radio or button group, above grid)
- New Game button (prominent, below or above grid)
- Mute toggle (corner icon)

### Navigation Flow
```
[Main Game Screen]
  → click card → [COMPARE state, locked briefly] → back to [Main Game Screen]
  → all pairs matched → [Win Overlay]
    → Play Again → [Main Game Screen, same difficulty]
    → change difficulty → [Main Game Screen, new difficulty]
  → New Game button → [Main Game Screen, reshuffled]
  → ? button → [How-To panel slides in/out]
```

### Onboarding / Tutorial (First-Time Flow)
1. Page loads → difficulty defaults to "Easy" (4×3 grid).
2. How-To panel is briefly visible for 3 seconds, then collapses. [Estimated]
3. Player sees 12 face-down cards with distinct card-back design.
4. First click → card flips with animation → hint text "Pick a second card" fades in briefly.
5. Second click → match or mismatch plays out naturally.
6. No further hand-holding — rules are self-evident from first interaction.

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- 2D flat layout. Top-down perspective on a virtual card table. Portrait-primary, responsive to landscape. [Confirmed]

### Art Style
- Flat / clean vector look. Casino aesthetic.
- **Palette:**
  - Table felt: #1a472a (dark green)
  - Card face: #fffef5 (warm white / cream)
  - Black suits (♠ ♣): #1a1a2e
  - Red suits (♥ ♦): #c0392b
  - Card border: #e0c97f (gold)
  - Card back: dark navy with repeating diamond pattern (CSS-generated)
  - Match glow: #f1c40f (gold) pulsing box-shadow
  - Win highlight: gradient gold/amber

### Card Design
- Card face: rank in top-left (large) and bottom-right (inverted), suit symbol centered (large Unicode pip), suit color coded.
- Card back: navy/dark blue with CSS geometric pattern (diamonds or crosshatch) — no external image.
- Card dimensions: responsive, roughly 60×90px on mobile, 70×105px on desktop.

### Animation & VFX
- **Flip:** CSS transform: rotateY(0→180deg), 0.4s ease, perspective 800px, transform-style: preserve-3d, backface-visibility: hidden on both faces.
- **Match:** matched pair glows gold (box-shadow pulse, 0.3s), then settles.
- **Mismatch:** brief red tint or shake (transform: translateX) on both cards before flip-back.
- **Win:** overlay fades in; stars animate in one-by-one; score numbers count up.
- **Board spawn:** cards fade in with staggered delay on new game.

### SFX (WebAudio — generated, no files)
- **Flip sound:** short ~80ms white-noise burst with fast attack/decay (card whoosh).
- **Match sound:** short ascending two-tone chime (~200ms).
- **Mismatch sound:** low soft thud (~150ms).
- **Win fanfare:** short ascending arpeggio (4–5 notes, ~600ms).
- All sounds: OscillatorNode + GainNode, created and destroyed per event.

### Music
- None (SFX only). [Estimated — keeps file self-contained]

---

## 11. Monetization

- **None.** The base clone has no ads, no IAP, no loot boxes, no subscriptions. [Confirmed — spec calls for free offline game]
- No ATT prompt, no GDPR/CMP consent flow required (no tracking, no ads, no data collection).
- No age gate required (ESRB E for everyone).

*Potential future monetization (not in base spec):*
- Remove-ads IAP if rewarded ads are added later.
- Premium card back theme packs.

---

## 12. Retention Hooks

### Personal Best (Primary Hook)
- Best moves and best time per difficulty saved to localStorage. Displayed in HUD. Drives repeat play. [Confirmed]

### Star Rating
- 1–3 stars per difficulty completion encourages optimization. [Estimated]

### Idle / Offline
- No idle or offline earnings — the game has no economy. Timer pauses when browser tab is backgrounded (or continues; either is acceptable). [Estimated]

### Push Notifications
- None (web game, no notification permission requested). [Confirmed]

### Energy / Lives / FOMO
- None. Unlimited play, no timewalls. [Confirmed]

### Daily / Login Rewards
- None in base spec. [Confirmed]

---

## 13. Localization & Accessibility

### Languages
- English only in base spec. [Estimated — text is minimal, easy to localize later]
- No RTL support needed for base spec.

### Text Scaling
- Font sizes use rem / clamp() for responsive scaling. All UI text is CSS-rendered (no baked-in text images).

### Colorblind Mode
- Risk: Red/Black color distinction is core to the match rule. Colorblind mitigation: suits ♠♣ vs ♥♦ already provide a shape-based distinction in addition to color, so standard colorblind players can use the suit symbol as a fallback. [Confirmed — suits disambiguate]
- No explicit colorblind mode toggle in base spec; consider adding "suit-only match" rule variant as an option. [Estimated]

### Difficulty / Assist Options
- Three grid sizes already function as difficulty levels.
- No additional assist modes in base spec.

### Age / Content Rating
- ESRB: E (Everyone). PEGI: 3.
- No COPPA compliance burden: game collects zero personal data, no accounts, no ads.
- No age gate required.

### Regional Differences
- None — offline web game.

---

## 14. Technical Structure

### Engine / Framework
- Vanilla HTML5 + CSS3 + JavaScript (ES6+). No framework, no build step. [Confirmed — spec requirement]

### Platforms
- Any modern browser (Chrome, Firefox, Safari, Edge) on mobile or desktop.
- Fully offline — no network requests after initial page load.

### Save System
- localStorage: saves best_moves and best_time per difficulty key.
- No accounts, no cloud sync, no auth.

### Multiplayer
- None. Single-player only. Anti-cheat/server authority: N/A.

### Backend Services
- None. Fully client-side.

### Analytics / SDKs
- None in base spec.

### Technical Implementation Notes
- CSS 3D flip: `perspective`, `transform-style: preserve-3d`, `backface-visibility: hidden`, `transform: rotateY(180deg)`.
- Shuffle: Fisher-Yates in-place shuffle of pair array.
- Compare lock: Boolean `isComparing` flag blocks card clicks during the mismatch reveal timeout.
- Timer: `setInterval` per second, cleared on win/new-game.
- WebAudio: AudioContext created on first user interaction (Chrome autoplay policy compliance).
- App size: Single HTML file, < 100 KB (no external assets).
- No console errors; valid HTML5 DOCTYPE.

---

## 15. Pacing & Difficulty

### Early Game (Easy — 4×3)
- 12 cards, 6 pairs. Short ~2-minute game. Good for first-time players. Forgiving memory load.
- "Aha moment": completing the board cleanly with a few lucky consecutive matches.

### Mid Game (Medium — 4×4)
- 16 cards, 8 pairs. ~3–5 minutes. Memory load is meaningful. Move optimization starts to matter.
- Churn point: players who randomly click will stall around 60% completion when few new cards are visible. Strategy (remembering flipped card positions) becomes rewarding.

### Late Game (Hard — 6×6)
- 36 cards, 18 pairs. ~6–10 minutes. Board fills the screen on mobile. Genuine memory challenge.
- Reward: clearing the full board feels like a significant accomplishment. Star ratings motivate repeat attempts.

### Difficulty Curve
- No procedural difficulty adjustment. Three fixed tiers. Player self-selects.
- "Wall" behavior: players hit a subjective wall when the grid size exceeds comfortable visual span (~5×5). The 6×6 is intentionally challenging.

### Pacing Tuning Knobs
- Mismatch reveal delay (currently 1000ms) — reduce to 800ms for "snappier" feel, increase to 1200ms for easier memorization. [Estimated]
- Star thresholds — tune based on playtesting. Values in Section 4 are starting estimates.

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---------|-----|------|
| 3-difficulty grid (Easy/Med/Hard) | ✓ | ✓ |
| Fisher-Yates shuffle | ✓ | ✓ |
| CSS 3D card flip animation | ✓ | ✓ |
| Match/mismatch detection | ✓ | ✓ |
| Compare lock (no 3rd flip) | ✓ | ✓ |
| Moves + timer HUD | ✓ | ✓ |
| Win detection + overlay | ✓ | ✓ |
| localStorage best score | ✓ | ✓ |
| WebAudio SFX | ✓ | ✓ |
| Mute toggle | ✓ | ✓ |
| Star rating on win | ✓ | ✓ |
| Responsive / mobile-first | ✓ | ✓ |
| How-to panel | ✓ | ✓ |
| Card back CSS design | ✓ | ✓ |
| Multiple card back themes | — | ✓ |
| High score leaderboard (online) | — | ✓ |
| Multiplayer mode | — | ✓ |
| Daily challenge | — | ✓ |
| Colorblind toggle | — | ✓ |
| Animations: board spawn stagger | ✓ | ✓ |

### Phased Build Roadmap

**Phase 1 — Core Engine (Day 1–2)**
- HTML structure: card grid container, HUD, difficulty selector.
- CSS: card flip 3D transform, card face/back design, responsive grid.
- JS: deck generation, pair selection, Fisher-Yates shuffle, card render.
- Game state machine: IDLE → PLAYING → COMPARE → WIN.
- Click handler with compare lock guard.

**Phase 2 — Game Logic & Scoring (Day 2–3)**
- Match/mismatch detection and outcome rendering.
- Mismatch reveal timeout and flip-back.
- Win detection (all pairs matched).
- Move counter, timer, win overlay with stats.
- Star rating calculation and display.

**Phase 3 — Persistence & Polish (Day 3–4)**
- localStorage save/load best score per difficulty.
- Best score display in HUD.
- WebAudio SFX: flip, match, mismatch, win.
- Mute toggle with state persistence.
- Mismatch shake animation, match glow animation.

**Phase 4 — UX & Responsiveness (Day 4–5)**
- Mobile-first responsive grid (CSS Grid, clamp sizing).
- Touch event handling and large tap targets.
- How-to panel with "?" button.
- New Game and difficulty-change flow with board rebuild.
- Polish: staggered card-deal animation, win overlay entrance.

**Phase 5 — Testing & Hardening (Day 5)**
- Cross-browser testing (Chrome, Firefox, Safari, Edge).
- Mobile testing (iOS Safari, Chrome Android).
- Edge cases: double-click rapid tap, change difficulty mid-game, resize window.
- Confirm no console errors. Validate HTML5.

### Recommended Tech Stack
- Vanilla HTML5 + CSS3 + ES6 JavaScript.
- CSS Grid for card layout (auto-fit / minmax for responsiveness).
- CSS custom properties (variables) for theming.
- Web Audio API (AudioContext) for SFX.
- localStorage for persistence.
- No frameworks, no bundlers, no external dependencies.

### Required Asset List
- **Art:** All CSS/Unicode — no image files required.
  - Card faces: HTML/CSS with Unicode suit symbols.
  - Card back: CSS geometric pattern (no image).
  - Table felt: CSS background color/gradient.
- **Audio:** WebAudio generated at runtime — no audio files.
- **Fonts:** System fonts (no web font import needed).
- **Data:** Deck defined in JS as constant array.

### Hardest Parts / Risks
1. **Compare lock correctness:** Race condition if player taps two cards in rapid succession before state updates. Must use a single `isComparing` flag checked synchronously before any flip is allowed.
2. **CSS 3D flip cross-browser:** Safari requires `-webkit-backface-visibility: hidden` and may behave differently with `transform-style: preserve-3d` inside flex/grid containers. Test explicitly.
3. **WebAudio autoplay policy:** AudioContext must be created or resumed inside a user gesture handler (Chrome/Safari policy). Defer AudioContext creation to first click.
4. **Responsive grid on small phones:** 6×6 Hard grid = 36 cards. At 320px viewport width each card is ~47px wide. Card text must scale down gracefully without becoming illegible.
5. **Timer cleanup:** `setInterval` must be cleared on every new game start and win state to prevent multiple concurrent timers and memory leaks.

---

## 17. Open Questions

1. **Pair definition variant:** Should Hard mode use exact-card pairs (e.g., only A♠+A♠) instead of rank+color pairs? Exact pairs would require duplicating cards and feel less like a real deck. Rank+color is the more elegant rule. [Decision: rank+color — recommended]
2. **Star thresholds:** The move counts in Section 4 are Estimated and need calibration against real playtesting — optimal play counts may differ. Set as configurable constants in code for easy tuning.
3. **Timer pause on tab-switch:** Whether to pause the timer when the browser tab loses focus is a UX judgment call. Pausing is fairer but adds complexity; most casual web implementations do not pause. [Recommendation: do not pause in base version]
4. **Card count for Hard:** 6×6 = 36 cards = 18 pairs. The full 52-card deck provides 26 unique rank+color pairs, so 18 is achievable. If future "Expert" mode targets 4×5=20 pairs or 5×6=30 pairs, the pair pool expands or shrinks accordingly — no design issue.
