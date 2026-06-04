# Solitaire (Klondike) — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

## 1. Snapshot

Klondike Solitaire is the definitive single-player patience card game, played with a standard 52-card deck. Seven tableau piles with face-down cards under one face-up card, four foundation piles, a stock pile, and a waste pile form the layout. The player builds foundations from Ace to King by suit, reorganizing tableau columns in alternating-color descending sequences to uncover buried cards. It is the game most people think of when they say "solitaire." [Confirmed]

**Quick facts:**
- Developer: Microsoft popularized the digital version (1990); now hundreds of independent implementations
- Platforms: Web, iOS, Android, Windows, macOS — universally available
- Genre: Card game / Patience / Puzzle
- Session length: 5–15 minutes per game
- Age/content rating: IARC Everyone (E) — no mature content
- Monetization model (original): Free-to-play with optional ads/IAP in modern mobile versions; this clone = ad-free, free-to-play

---

## 2. Core Loops

- **30-second loop:** Click/drag a card or stack, place it on a legal tableau target or foundation, flip any newly exposed face-down card, draw from stock if no useful move available.
- **Session loop:** Deal a shuffled deck, work through the tableau to expose and move Aces to foundations, cycle stock for useful cards, continue building foundation stacks up to King, win (or restart if stuck). One game = ~5–15 minutes.
- **Meta loop:** Compare best time and best score across sessions saved in localStorage; try to beat personal records. No long-term progression beyond statistics; the appeal is variety from random shuffles and the zen of a clean solve. [Confirmed]

---

## 3. Mechanics, Controls & Game States

### Core Rules [Confirmed]

**Deal:**
- 1 card face-up on pile 1, then 1 face-down + 1 face-up on pile 2, etc. — pile N gets N–1 face-down + 1 face-up.
- Remaining 24 cards go face-down to the stock.

**Tableau rules:**
- Build DOWN in ALTERNATING COLORS (red on black, black on red).
- Suits within tableau do not matter, only color matters.
- Only a King (or a sequence headed by a King) may be placed on an empty tableau pile.
- Any face-up sequence of legally stacked cards may be moved as a unit.

**Foundation rules:**
- Four foundation piles, one per suit (♠ ♥ ♦ ♣).
- Build UP by SUIT from Ace (A, 2, 3 … Q, K).
- An Ace must be the first card placed on each foundation.
- Cards may be moved back from foundation to tableau (legal but penalized in scoring).

**Stock & waste:**
- Draw 1: flip one card at a time from stock to waste.
- Draw 3: flip three cards at a time; only the top card of the waste is playable.
- When stock is exhausted, flip the entire waste pile back to form a new stock (no re-shuffle).
- Draw-1 mode: unlimited recycles (standard casual rule) [Estimated — some strict versions allow only 1 pass].
- Draw-3 mode: unlimited recycles [Estimated; some strict versions allow only 3 passes].

**Auto-flip:** When a face-down card becomes the top card of a tableau pile, it flips face-up automatically.

**Win condition:** All 52 cards placed on the four foundations (each foundation A→K same suit).

**Fail condition:** No win condition — the player continues until they win or choose to restart. No lose state beyond getting stuck and manually restarting.

### Game Modes
| Mode | Description |
|---|---|
| Draw 1 | One card drawn per stock tap; easier |
| Draw 3 | Three cards drawn per stock tap; only top of waste usable; harder |
| Timed | Timer running, score adjusted by time taken [Estimated] |
| Vegas (optional) | Start –$52, earn +$5 per foundation move, win = positive total [Estimated bonus mode] |

### Controls
- **Desktop:** Click to select a card/stack; click legal target to move; drag-and-drop cards and sequences.
- **Mobile:** Tap to select, tap destination to place; drag-and-drop with touch.
- **Double-click / double-tap:** Auto-send card to the correct foundation if legal.
- **New Game button:** Re-deals a fresh shuffled deck.
- **Undo button:** Reverts the last action (stack-based, minimum 1 level, ideally unlimited).
- **Draw-mode toggle:** Switch Draw 1 / Draw 3.
- **Mute toggle:** Silence audio.

### Feedback Systems
- Card flip sound on reveal.
- Card place sound on legal drop.
- Draw sound on stock tap.
- Foundation place sound (distinct, rewarding).
- Invalid move shake animation.
- Win animation: cards cascade / explode from foundations.
- Score ticks up visually on point events.

---

## 4. Progression

No persistent unlock trees or level progression. [Confirmed]

**In-game progression tracking:**
- Timer (seconds elapsed this game).
- Move counter (increments on each action).
- Score (accumulates per scoring events).
- Best time / best score persisted to localStorage.

**Difficulty gating:**
- Draw-3 mode is inherently harder — fewer accessible cards per cycle.
- No separate difficulty levels otherwise; randomness provides natural variance.

---

## 5. Economy & RNG

### Currencies
| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| Score points | Soft in-game | Card moves (see table) | Not spendable; display only |

No hard/premium currency — this is a free, no-monetization clone. [Confirmed for classic implementations]

### Scoring Table (Standard / Microsoft-style) [Confirmed]
| Event | Points |
|---|---|
| Waste → Foundation | +10 |
| Waste → Tableau | +5 |
| Tableau → Foundation | +10 |
| Foundation → Tableau (undo/manual) | −15 |
| Flip a hidden card | +5 |
| Time bonus (end of game) | +700000 / (seconds elapsed) [Estimated] |
| Each second elapsed after 30s | −2 [Estimated, some implementations] |

### Vegas Scoring (optional mode) [Confirmed]
| Event | Value |
|---|---|
| Start of game | −$52 |
| Card moved to foundation | +$5 |
| Break-even threshold | 11 cards to foundation |

### RNG
- Full 52-card Fisher-Yates shuffle at deal. [Confirmed]
- No drop rates, gacha, or loot tables — pure shuffle-based randomness.
- Approximately 79% of Klondike deals are theoretically winnable (exact figure debated; practical win rate for average players is ~43%). [Estimated / research-disputed]

---

## 6. Content Inventory

### Deck Composition [Confirmed]
- 52 cards: 4 suits × 13 ranks (A, 2–10, J, Q, K).
- Suits: ♠ Spades (black), ♥ Hearts (red), ♦ Diamonds (red), ♣ Clubs (black).

### Layout at Deal
| Zone | Count | Description |
|---|---|---|
| Tableau piles | 7 | Piles 1–7, each with 1–7 cards (28 total); 21 face-down, 7 face-up |
| Stock | 24 | Remaining cards, all face-down |
| Waste | 0 | Empty at start |
| Foundations | 4 | Empty at start |

### Total UI Elements
- 52 card objects + 4 empty foundation slots + 7 tableau column drop zones + 1 stock + 1 waste.

---

## 7. Theme, Narrative & Tone

- **Setting:** Classic casino/card table aesthetic — green felt background, red/white/black cards with traditional suit symbols.
- **Premise:** No story. The game is purely mechanical. [Confirmed]
- **Characters:** None. [Confirmed]
- **Tone:** Relaxing, meditative, solitary. Appeals to a broad casual audience. Familiarity is a core feature — most players already know the rules from Windows 3.1 / XP era.
- **Writing style:** Minimal UI text (button labels, score labels). No flavor text needed.
- **IP:** No licensed IP; the game itself is in the public domain as a card game mechanic. Digital implementations have their own copyrights but mechanics are free to clone. [Confirmed]

---

## 8. Meta & Social Systems

**Statistics (local):**
- Best time (this session / all-time), best score, games played, games won, win percentage — all localStorage. [Estimated standard feature set]

**No social/multiplayer systems in the base game.** [Confirmed — Klondike is a single-player patience game]

**Optional features (clone additions):**
- Daily challenge seed (share a specific shuffle seed so friends play the same deal).
- Hint system (highlight one legal move).
- Auto-complete (when all remaining cards can be placed automatically, offer to finish).

**Live-ops:** None — no ongoing-content burden. Static game. [Confirmed]

---

## 9. UI / UX & Screen Map

### Screens
| Screen | Purpose |
|---|---|
| Game (main/only) | Full gameplay canvas with all zones visible |
| Win overlay | Celebration animation + score + time + replay/new-game buttons |
| How-to modal | Rules quick reference, dismissible |
| Settings panel | Draw mode, sound, reset stats |
| Stats modal | Games played, won, best time, best score |

### HUD Elements During Gameplay
- Top bar: [New Game] [Undo] [Draw Mode toggle] — [Timer] [Moves] [Score] [Best]
- Stock pile (tap to draw)
- Waste pile (top card visible, full fan visible for draw-3 context)
- 4 foundation slots labeled ♠ ♥ ♦ ♣ with current top card or suit placeholder
- 7 tableau columns with cards

### Navigation Flow
```
Game Screen ──► Win Overlay ──► New Game ──► Game Screen
     │
     ├──► How-To Modal (dismissed → Game Screen)
     └──► Settings Panel (dismissed → Game Screen)
```

### Onboarding / Tutorial (First-Time User)
1. Splash: brief title + "Tap to play" (or skip directly to game).
2. Deal animation (cards fly to positions).
3. Optional hint arrow pointing to the first auto-detectable legal move (e.g., "Move this Ace to foundation").
4. No mandatory tutorial — Klondike players are assumed to know the rules or will read How-To.
5. How-To button always visible in top bar for reference.

---

## 10. Art, Audio, Camera & Feel

### Dimensions & Camera
- 2D, top-down perspective (standard card game view). [Confirmed]
- Portrait on mobile, landscape on desktop (responsive — game scales to fit). [Estimated best practice]
- No camera movement; static layout.

### Art Style
- Classic green felt background (#35654d or similar).
- Cards: white/cream face, red (♥♦) or black (♠♣) pips, rank text at top-left and bottom-right.
- Face-down card: solid color back (blue or red pattern, CSS-generated).
- Rounded card corners (border-radius).
- Drop shadows on cards for depth.
- Empty pile: dashed border or faint suit watermark.

### Color Palette [Estimated]
| Element | Color |
|---|---|
| Felt background | #35654d (casino green) |
| Card face | #fefefe |
| Card back | #2563eb (royal blue) |
| Red suits ♥♦ | #dc2626 |
| Black suits ♠♣ | #111111 |
| Top bar | #1a3a2a |
| Accent (selected) | #f59e0b (amber glow) |

### Animation & VFX
- Deal animation: cards slide/fly to positions from a central deck.
- Flip animation: CSS transform scaleX (card "turns over").
- Drag: card lifts (slight scale-up + shadow), snaps to destination.
- Invalid move: brief shake/bounce on the dragged card.
- Foundation place: subtle pulse/glow.
- Win: cards cascade/bounce off foundations in a victory shower (classic Windows-style).

### Audio (Web Audio API, generated) [Estimated]
| Sound | Trigger |
|---|---|
| Soft thud | Card placed on tableau |
| Crisp click | Card drawn from stock |
| Higher click | Card placed on foundation |
| Flip swoosh | Face-down card auto-flips |
| Fanfare chord | Win |
| Mute toggle available | User preference stored in localStorage |

---

## 11. Monetization

**This clone: no monetization.** Free, offline, no ads, no IAP. [Confirmed design decision]

For reference — original commercial implementations:
| Platform | Typical Model |
|---|---|
| Mobile (iOS/Android) | Rewarded video ads + remove-ads IAP ($1.99–$4.99) |
| Web | Display banner ads + optional donation |
| Windows (modern) | Free with optional Microsoft 365 upsell |

**Consent / ATT / CMP:** Not applicable for this ad-free clone. If ads are later added, GDPR CMP + iOS ATT prompt would be required before any tracking.

---

## 12. Retention Hooks

**This is a casual single-player puzzle; retention is low-friction.** [Confirmed]

| Hook | Implementation |
|---|---|
| Best score / best time | localStorage comparison; shown at win screen |
| Quick restart | One tap to deal a new game |
| Draw-mode variety | Draw-1 (easy) vs Draw-3 (hard) keeps it fresh |
| Hint system | Optional highlight of a valid move to prevent frustration |
| Auto-complete | When win is inevitable, offer to finish automatically — satisfying payoff |

**No energy/lives system, no offline earnings, no push notifications.** The game is purely on-demand. [Confirmed]

---

## 13. Localization & Accessibility

### Localization
- English only for this clone (minimal text — score labels, button labels). [Estimated]
- Expanding to other languages is trivial due to low text count.
- RTL support not required (no text-heavy layout). [Estimated]
- No regional content differences. [Confirmed]

### Accessibility
- Color-blind mode consideration: suits use both color AND symbol (♠♥♦♣), so color-blind users can distinguish suits by symbol. [Confirmed — standard good practice]
- Large tap targets for mobile (minimum 44×44 px per card interaction).
- Keyboard navigation: optional (Tab to focus, Enter to select/place).
- Text scaling: score/timer use relative units.
- No age-gate required — IARC E rating, no data collection, no ads. [Confirmed]
- COPPA/GDPR-K: not applicable — no user data collected, no account system. [Confirmed]

---

## 14. Technical Structure

### Engine / Stack
- **Vanilla HTML5 + CSS3 + ES6 JavaScript.** No framework, no build step. [Confirmed design requirement]
- Single self-contained `.html` file; inline `<style>` and `<script>`.

### Platforms
- Any modern browser (Chrome, Firefox, Safari, Edge). [Confirmed]
- Fully offline — no network requests after initial load.
- Responsive: `<meta name="viewport" content="width=device-width, initial-scale=1">`.

### Save System
- `localStorage` keys: best score, best time, draw-mode preference, mute preference, in-progress game state (optional).
- No cloud sync, no account/auth. [Confirmed]

### Card Rendering
- Pure CSS + Unicode suit symbols. No external images, no canvas (optional for win animation). [Confirmed]

### Performance
- 52 DOM elements (cards) + ~20 UI elements. Trivially fast. No server required.
- Fisher-Yates shuffle in JS.
- Event delegation for drag-and-drop and tap handling.

### Anti-Cheat / Multiplayer
- N/A — single-player, offline, no server. [Confirmed]

### Analytics / SDKs
- None for this clone. [Confirmed]

---

## 15. Pacing & Difficulty

### Early Game (first ~2 minutes)
- Deal reveals 7 face-up cards. Player scans for Aces, Kings, and useful sequences.
- First moves are often obvious — move Aces, build tableau.
- The "aha" moment: first card flipped face-up from a buried stack.

### Mid Game (~2–8 minutes)
- Stock cycling becomes critical — player works through draw pile to find needed cards.
- Decisions about when to move a card to foundation vs. keeping it for tableau building.
- Chains of flips unlock buried sections.
- Tension point: empty tableau columns — should you fill with a King now or wait?

### Late Game (~8–15 minutes)
- Most cards revealed; foundation-building accelerates.
- Either the game resolves cleanly (auto-complete available) or the player is stuck with no legal moves and must restart.
- Win animation provides satisfying closure.

### Churn Points [Confirmed from player reviews]
- Early abandonment: if no Aces appear in first few stock cycles, frustration rises.
- Mid-game deadlock: player fills all empty tableau columns with Kings, blocking useful moves.
- Draw-3 cycling: players lose track of which cards are accessible, leading to confusion.
- **Mitigation:** Hint button, undo, and auto-complete dramatically reduce frustration churn.

---

## 16. Clone Build Plan

### MVP Feature Set
- [ ] 52-card deck, Fisher-Yates shuffle, deal animation
- [ ] 7 tableau piles with correct face-down/face-up deal
- [ ] 4 foundation piles, 1 stock, 1 waste
- [ ] All legal move rules enforced (tableau alternating-color descending, foundation ascending by suit)
- [ ] Multi-card stack moves
- [ ] Auto-flip newly exposed face-down cards
- [ ] Stock → waste draw (Draw 1 and Draw 3 toggle)
- [ ] Waste recycle (flip waste back to stock)
- [ ] Double-click / double-tap → auto-move to foundation
- [ ] Win detection + win overlay
- [ ] Undo (1-level minimum, ideally unlimited)
- [ ] New Game button
- [ ] Timer + move counter + score display
- [ ] Best time / best score (localStorage)
- [ ] Drag-and-drop (mouse + touch)
- [ ] Click-to-select + click-to-place
- [ ] Responsive layout (mobile + desktop)
- [ ] Web Audio SFX (card place, flip, draw, win)
- [ ] Mute toggle

### Full Feature Set (beyond MVP)
- [ ] Unlimited undo stack
- [ ] Auto-complete (when win is guaranteed)
- [ ] Hint system (highlight one valid move)
- [ ] Deal animation (cards fly to positions)
- [ ] Win cascade animation (classic bouncing cards)
- [ ] Vegas scoring mode
- [ ] Daily challenge (fixed seed by date)
- [ ] Statistics modal (win %, avg time, games played)
- [ ] Save in-progress game state to localStorage

### Phased Roadmap
| Phase | Deliverables | Est. Time |
|---|---|---|
| 1 — Core engine | Deck model, shuffle, deal, move-legality rules, state machine | 4h |
| 2 — Rendering | Card CSS, layout, face/back styles, responsive grid | 3h |
| 3 — Interaction | Drag-and-drop (mouse + touch), click-select, double-click to foundation | 4h |
| 4 — Game logic | Stock/waste draw (D1+D3), recycle, auto-flip, win detection, undo | 3h |
| 5 — Scoring & persistence | Timer, moves, score events, localStorage best | 2h |
| 6 — Polish | SFX, animations, win overlay, mobile scaling, mute | 3h |
| 7 — Extras | Hints, auto-complete, stats modal, deal animation | 3h |

### Recommended Tech Stack
- HTML5 + CSS3 (Grid/Flexbox for layout, transforms for animation)
- Vanilla ES6 JS (no frameworks needed at this scale)
- Web Audio API (generated beeps/tones, no external files)
- localStorage API for persistence
- No build step — deliverable is a single `.html` file

### Required Asset List
| Asset | Source |
|---|---|
| Card faces (rank + suit) | CSS + Unicode (♠♥♦♣) |
| Card back pattern | CSS gradient or SVG inline |
| Felt background | CSS color (#35654d) |
| SFX | Web Audio API synthesized |
| Icons (mute, undo, etc.) | Unicode symbols or inline SVG |

### Hardest Parts / Risks
1. **Multi-card stack drag-and-drop on mobile** — touch events, offset calculation, and preview positioning. Requires careful `touchstart/touchmove/touchend` handling.
2. **Draw-3 waste display** — showing the fan of 3 cards (last 3 of waste visible, only top playable) requires careful z-index and layout.
3. **Undo with stock/waste** — undoing a draw must restore the waste/stock state, not just the card positions; requires snapshotting full game state.
4. **Auto-complete detection** — determining when all remaining moves are forced wins requires simulating forward, or using the heuristic that all cards are face-up and foundation-building is unblocked.
5. **Responsive layout** — 7 tableau columns + 6 top-bar slots must fit on a 375px-wide phone without horizontal scroll; card width must scale dynamically.

---

## 17. Open Questions

1. **Win rate / solvability seed filtering** — Should unsolvable deals be filtered out? (Requires forward-solving, which is computationally expensive; most implementations just allow all shuffles.) [Estimated: allow all; add replay option]
2. **Draw-1 recycle limit** — Some purists allow only 1 pass through stock in Draw-1 mode. The clone defaults to unlimited recycles; this could be a toggle. [Estimated]
3. **Exact Windows XP scoring formula** — The precise time-bonus formula varies by implementation. The value `700000 / seconds` is commonly cited but not officially published by Microsoft. [Estimated]
4. **Auto-complete threshold** — Exactly when to offer auto-complete (all cards face-up? all cards in tableau/waste accessible?) needs playtesting to feel right.
