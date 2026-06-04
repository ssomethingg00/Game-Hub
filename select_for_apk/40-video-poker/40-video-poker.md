# Video Poker (Jacks-or-Better 9/6) — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Video Poker (Jacks-or-Better) is the canonical casino electronic card game that fuses five-card draw poker with a slot-machine form factor. The player places a bet of 1–5 credits, receives five cards, chooses which to hold, draws replacements, and is paid according to a fixed paytable if the final hand ranks as Jacks-or-Better or higher. The "9/6" label means a Full House pays 9× and a Flush pays 6× the bet — the full-pay variant with ~99.54% theoretical RTP under optimal play.

**Quick facts:**
- **Original form:** Physical video poker cabinet (IGT, Bally, etc.); widely cloned as browser/mobile
- **Genre:** Casino card game / skill-based gambling sim
- **Platforms:** Web, iOS, Android, land-based cabinet
- **Release:** 1970s cabinets; modern web clones ongoing [Confirmed]
- **Age/content rating:** 17+ (ESRB MATURE) — simulated gambling [Confirmed]
- **Monetization (this clone):** Free-to-play with localStorage persistence; no real money

---

## 2. Core Loops

- **30-second loop:** Place bet → press Deal → see 5 cards → tap cards to toggle HOLD → press Draw → view result + payout → credits update → repeat.
- **Session loop:** Start with 1,000 credits; grind through hands, adjusting bet size, chasing big hands (Royal Flush, Straight Flush, Four of a Kind); session ends when player stops or goes broke (resets to 1,000).
- **Meta loop:** Cumulative stats (hands played, biggest win, best hand hit) tracked in localStorage; player improves strategy knowledge over sessions; chases the max-bet Royal Flush bonus (4,000 credits).

---

## 3. Mechanics, Controls & Game States

### Core Mechanics
- **52-card deck:** Standard deck, shuffled fresh each hand using Fisher-Yates.
- **Bet selection:** Player sets bet 1–5 credits per hand before dealing. "Max Bet" shortcut sets bet to 5 and immediately deals.
- **Deal phase:** 5 cards dealt face-up from the shuffled deck.
- **Hold phase:** Player taps/clicks any card(s) to toggle HOLD/UNHOLD. A "HELD" label visually marks held cards.
- **Draw phase:** Non-held cards are replaced by the next cards from the same shuffled deck (not reshuffled). Held cards stay.
- **Hand evaluation:** Final 5-card hand is scored against the paytable. Payout = multiplier × bet. Royal Flush at 5-coin bet pays flat 4,000 (bonus).
- **Credits:** Start at 1,000. Bet is deducted on Deal. Winnings are added after Draw. If credits reach 0, a "broke" reset triggers.

### Game States
1. **IDLE** — awaiting bet/deal input.
2. **DEAL** — cards dealt, awaiting hold selections.
3. **DRAW** — player has pressed Draw; cards flipped; result evaluated; banner shown.
4. **BROKE** — 0 credits; modal asks to reset to 1,000.

### Controls
| Action | Desktop | Mobile |
|---|---|---|
| Increase bet | Click +/- chip buttons | Tap chip buttons |
| Max Bet + Deal | Click "Max Bet" | Tap "Max Bet" |
| Deal / Draw | Click "Deal" / "Draw" | Tap button |
| Toggle HOLD | Click card | Tap card |
| Mute audio | Click speaker icon | Tap icon |

### Input Scheme
- Landscape-friendly on mobile; portrait also supported via responsive CSS.
- All touch targets ≥ 48px tall.

### Win/Lose/Fail
- **Win:** Final hand matches paytable entry; credits += payout.
- **Push/Loss:** Hand below Jacks-or-Better; credits unchanged beyond the bet already deducted.
- **Broke:** Credits = 0; reset modal shown; clicking Reset restores 1,000 credits.

### Feedback
- Winning paytable row highlights in gold.
- Result banner shows hand name + win amount.
- WebAudio SFX: card deal click, hold toggle, draw whoosh, win chime, big win fanfare, broke buzz.
- Winning cards pulse with CSS animation.

---

## 4. Progression

- **No XP/levels** — this is a pure skill/luck loop [Confirmed].
- **Progression metric:** Credit balance and lifetime stats (best hand, biggest single win, total hands, total won).
- **Unlock gating:** None — all features available from start.
- **Soft progression:** Player knowledge of optimal hold strategy; the gap between novice (~97%) and optimal (~99.54%) RTP represents skill growth [Estimated].
- **Persistence:** Stats and credits saved to localStorage between sessions.

---

## 5. Economy & RNG *(tables)*

### Currencies

| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| Credits | Soft (simulated) | Winning hands, reset grant | Bets per hand |

### 9/6 Jacks-or-Better Paytable

| Hand | 1 Coin | 2 Coins | 3 Coins | 4 Coins | 5 Coins (Max) |
|---|---|---|---|---|---|
| Royal Flush | 250 | 500 | 750 | 1,000 | **4,000** |
| Straight Flush | 50 | 100 | 150 | 200 | 250 |
| Four of a Kind | 25 | 50 | 75 | 100 | 125 |
| Full House | 9 | 18 | 27 | 36 | 45 |
| Flush | 6 | 12 | 18 | 24 | 30 |
| Straight | 4 | 8 | 12 | 16 | 20 |
| Three of a Kind | 3 | 6 | 9 | 12 | 15 |
| Two Pair | 2 | 4 | 6 | 8 | 10 |
| Jacks or Better | 1 | 2 | 3 | 4 | 5 |
| Other | 0 | 0 | 0 | 0 | 0 |

[Confirmed — standard 9/6 Jacks-or-Better paytable]

### Royal Flush Bonus Logic
- Bets 1–4: Royal Flush pays 250 × bet (linear, 250 per coin).
- Bet 5 (max): Royal Flush pays flat **4,000** (800 per coin — the bonus).
- All other hands: always pay multiplier × bet linearly.

### RNG
- No gacha or loot tables.
- Pure Fisher-Yates shuffle of 52-card deck each hand. Each card equally likely. [Confirmed — standard poker probability]
- Expected Royal Flush frequency: ~1 in 40,391 hands at optimal play [Confirmed].
- RTP at optimal play: **99.54%** [Confirmed].
- RTP at random play: ~95–97% [Estimated].

### Starting Economy
- Starting credits: **1,000** [Estimated — genre standard].
- Broke reset grant: **1,000 credits** [Estimated].

---

## 6. Content Inventory *(counts + lists)*

### Cards
- 52 cards: 4 suits (♠ ♥ ♦ ♣) × 13 ranks (2–10, J, Q, K, A).
- Rendered with CSS + Unicode — no image assets.
- Red suits: ♥ ♦. Black suits: ♠ ♣.

### Hand Rankings (9 paying hands + 1 losing)
1. Royal Flush (A-K-Q-J-10 same suit)
2. Straight Flush (5 consecutive same suit, non-royal)
3. Four of a Kind
4. Full House (three of a kind + pair)
5. Flush (5 same suit, non-straight)
6. Straight (5 consecutive, mixed suits; ace-high and ace-low both valid)
7. Three of a Kind
8. Two Pair
9. Jacks or Better (one pair, J/Q/K/A)
10. Losing hand (no qualifying pair or better)

### Game Modes
- Single mode: Jacks-or-Better 9/6, indefinite play [Confirmed].
- (No tournament, no multi-hand, no wild-card variant in MVP.)

---

## 7. Theme, Narrative & Tone

- **Setting:** Casino card table aesthetic — dark green felt, gold accents, casino chip colors.
- **Premise:** None — pure mechanical casino game. No story, no characters.
- **Tone:** Sleek, confident, slightly glamorous. Imitates the visual calm of a real casino cabinet.
- **Color palette:** Deep green (#1a472a), gold (#c9a84c), near-black (#0d1f18), white card faces, red for hearts/diamonds.
- **Writing style:** Minimal UI copy; hand names spelled out in result banner; no mascots.

---

## 8. Meta & Social Systems

- **Stats tracking:** Hands played, total won, best hand achieved, biggest single win — displayed in stats panel [Estimated].
- **No daily rewards / login streaks** — pure session game.
- **No multiplayer, leaderboards, guilds, or PvP** [Confirmed — single-player only].
- **No live-ops / events** — static single-player [Confirmed].
- **No sharing / referral features** in MVP.

---

## 9. UI/UX & Screen Map

### Screen List
| Screen | Purpose |
|---|---|
| Main Game Screen | Single-screen app: paytable, cards, controls, stats all in one view |
| Broke Modal | Overlay when credits = 0; "Reset to 1,000 credits" button |
| How-to Tooltip/Panel | Collapsible rules panel explaining hold/draw flow |
| Settings Row | Inline controls: mute toggle, stats display |

### Navigation Flow
- One screen; no routing. All panels in-page. Broke modal is a CSS overlay.

### HUD Elements (during DEAL/DRAW phases)
- Paytable (always visible, left or top) with winning row highlighted
- 5 card slots with rank/suit display and HELD label
- Credits counter
- Current bet display
- Last win amount
- Deal/Draw button (label changes by state)
- Bet controls (chip buttons, Max Bet)
- Mute button
- Result banner (fades in after Draw)

### Onboarding / Tutorial (step-by-step)
1. On first load: "How to Play" panel auto-expanded, showing 4 rules.
2. Rule 1: Set your bet (1–5 credits) using the chip buttons or Max Bet.
3. Rule 2: Press Deal to receive 5 cards.
4. Rule 3: Tap cards to HOLD them — held cards are kept on the draw.
5. Rule 4: Press Draw to replace non-held cards. Win is paid if your final hand qualifies.
6. Panel collapses after first Deal press; accessible via "?" button anytime.

### Settings
- Mute toggle (WebAudio on/off)
- Stats: Hands played, Total won, Best hand, Biggest win (shown inline)

---

## 10. Art, Audio, Camera & Feel

- **Dimension:** 2D, flat web layout. No camera — static DOM.
- **Art style:** CSS-only flat design; cards are white rectangles with rank + suit Unicode glyph. No images.
- **Card design:** White card face; top-left rank+suit, bottom-right rank+suit (rotated 180°); large central suit glyph. Red for ♥♦, black for ♠♣.
- **Animation / VFX:**
  - Card deal: CSS slide-in + fade from deck position.
  - Hold toggle: card lifts (translateY, box-shadow) + "HELD" label appears.
  - Draw: non-held cards flip out (rotateY), new card flips in.
  - Win: winning cards pulse (scale keyframe). Result banner slides in.
  - Big win (Straight Flush+): brief screen-edge gold glow.
- **SFX (WebAudio synthesized — no files):**
  - `dealClick`: short percussive click per card.
  - `holdToggle`: soft thud.
  - `drawWhoosh`: brief filtered noise sweep.
  - `winChime`: ascending sine tones for small wins.
  - `bigWinFanfare`: chord arpeggio for Four-of-a-Kind+.
  - `brokeBuzz`: descending buzz.
- **Music:** None (casino ambient optional future feature).
- **Mute toggle:** Global gain node set to 0/1.

---

## 11. Monetization

- **This clone:** Free-to-play, no real money, no ads, no IAP [Confirmed by spec].
- **Simulated gambling only** — credits are fictional with no cash value.
- **Original cabinet monetization:** Coin-operated physical machine, real money [Confirmed].
- **ATT/CMP/consent:** Not applicable — no ads, no tracking, no real money.
- **Age-gating:** Game should display "18+ Simulated Gambling — No Real Money" disclaimer in footer [Estimated — best practice].

---

## 12. Retention Hooks

- **No idle/offline earnings** — active play only [Confirmed — genre standard].
- **No push notifications** (web game, no service worker in MVP).
- **No energy/lives system.**
- **Retention via bankroll persistence:** Credits saved in localStorage encourage returning to continue the session.
- **Retention via stat chase:** Lifetime stats (best hand, biggest win) create a light trophy system.
- **FOMO hook:** Max-bet Royal Flush (4,000 credits) is the aspirational jackpot; paytable always visible.

---

## 13. Localization & Accessibility

- **Language:** English only in MVP [Estimated].
- **RTL:** Not required for MVP.
- **Text scaling:** Viewport-relative font sizes (vw/rem); card text scales with cards.
- **Colorblind support:** Suits use both color AND symbol (♠♥♦♣) — shape-discriminable without color. Red/black distinction is secondary to symbol.
- **Touch target size:** All interactive elements ≥ 48×48px.
- **Keyboard:** Spacebar = Deal/Draw; number keys 1–5 = set bet; H key = hold focused card [Estimated — accessibility enhancement].
- **Screen reader:** ARIA labels on card buttons; result announced via aria-live region.
- **Age/content rating:** ESRB 17+ / Simulated Gambling. No COPPA concern (17+ game). GDPR: no personal data collected; localStorage only.
- **Regional:** No real-money wagering; no gambling license required for free-play clone.

---

## 14. Technical Structure

- **Engine/Framework:** Vanilla HTML5 + CSS3 + ES6 JavaScript. No build step. [Confirmed by spec]
- **Single file:** One `.html` with inline `<style>` and `<script>`.
- **Platforms:** Any modern browser (Chrome, Firefox, Safari, Edge). Works offline by double-click.
- **Save system:** `localStorage` — saves credits, stats. Keys: `vp_credits`, `vp_stats`.
- **Accounts/auth:** None — anonymous local play only.
- **Cross-device sync:** None in MVP.
- **Multiplayer/netcode:** N/A — single-player [Confirmed].
- **Anti-cheat:** N/A — no competitive/monetary stakes [Confirmed].
- **Analytics/SDKs:** None — no external resources loaded [Confirmed by spec].
- **Audio:** WebAudio API — fully synthesized, no audio files, no network requests.
- **Performance:** <500KB total. No canvas — pure DOM/CSS. Targets 60fps animations.
- **Compatibility:** `<meta viewport>` for mobile; CSS Grid/Flexbox layout; no ES2022+ features without fallback.

---

## 15. Pacing & Difficulty

### Early Game (0–50 hands)
- Player learns hold mechanics; may not know optimal strategy.
- Frequent small wins (Jacks-or-Better, Two Pair, Three of a Kind) keep credits roughly stable.
- RTP ~95–97% for naive play — slow credit drain.

### Mid Game (50–500 hands)
- Player refines strategy — recognizing flush draws, holding high pairs.
- RTP approaches 99%+ as strategy improves.
- Credit variance high — swings common due to Four-of-a-Kind / Straight Flush rarity.

### Late / Expert Play
- Near-optimal (~99.54% RTP) — near break-even long term.
- Royal Flush chase is the "endgame" milestone; expected ~1 per 40,000 hands.
- Player may set Max Bet permanently for Royal Flush eligibility.

### Difficulty Curve
- No difficulty modes — poker math is fixed [Confirmed].
- Churn point: Going broke within first 50 hands (happens to naive players) — mitigated by reset-to-1,000 mechanic.
- "Aha" moments: First Straight Flush hit; first Four of a Kind; approaching Royal Flush.

---

## 16. Clone Build Plan

### MVP Feature Set
- [x] 52-card deck with Fisher-Yates shuffle
- [x] Bet 1–5 (chip +/- buttons, Max Bet)
- [x] Deal 5 cards
- [x] Hold toggle per card with visual indicator
- [x] Draw replaces non-held cards from same deck
- [x] Correct hand evaluator (all 9 paying hands, ace-high + ace-low straights, J/Q/K/A pair minimum)
- [x] 9/6 paytable with per-bet payout display
- [x] Royal Flush max-bet bonus (4,000)
- [x] Credits tracking, deduct bet on deal, add win on draw
- [x] Broke detection and reset
- [x] Winning row highlight in paytable
- [x] Result banner with hand name
- [x] localStorage persistence (credits + stats)
- [x] Responsive mobile-first layout
- [x] WebAudio SFX (synthesized) + mute toggle
- [x] CSS card rendering with Unicode suits

### Full Feature Set (post-MVP)
- [ ] Multi-hand video poker (3/5/10 hands simultaneous)
- [ ] Additional variants (Deuces Wild, Double Bonus)
- [ ] Optimal strategy hints (highlight cards to hold)
- [ ] Hand history log
- [ ] Configurable paytables
- [ ] Leaderboard (server-backed)
- [ ] Keyboard shortcut full support
- [ ] Animated card flip (3D CSS)

### Phased Roadmap

| Phase | Deliverable |
|---|---|
| 1 | Deck model, shuffle, deal/draw logic, hand evaluator (unit-testable) |
| 2 | DOM card rendering (CSS + Unicode), hold toggle, draw flow |
| 3 | Paytable display, payout calculation, credits/bankroll system |
| 4 | Result banner, paytable row highlight, broke/reset flow |
| 5 | Responsive layout, mobile touch targets, viewport scaling |
| 6 | WebAudio SFX synthesis, mute toggle |
| 7 | localStorage persistence, stats panel |
| 8 | Polish: animations, casino palette, how-to panel |

### Recommended Tech Stack
- HTML5 + CSS3 (Grid, Flexbox, CSS custom properties, keyframe animations)
- Vanilla ES6+ JavaScript (no frameworks)
- WebAudio API for SFX
- localStorage for persistence

### Required Asset List
- Zero external assets — all CSS + Unicode + WebAudio synthesis [Confirmed by spec]

### Hardest Parts / Risks
1. **Hand evaluator correctness** — ace-low straight (A-2-3-4-5) vs ace-high (10-J-Q-K-A), correct Jacks-or-Better pair detection (not tens-or-better), Royal Flush being a special case of Straight Flush. Must be airtight.
2. **Royal Flush payout branching** — the 4,000 flat vs 250×bet logic must be clearly separated from the linear multiplier table.
3. **Mobile card layout** — 5 cards + paytable on a 375px-wide phone requires careful CSS; cards must remain tap-able at adequate size.
4. **Hold/draw deck integrity** — same 52-card deck used for both deal and draw; deck index must advance correctly; reshuffling only between hands.
5. **Paytable column display** — showing all 5 bet columns or dynamically switching to current-bet column while keeping readability.

---

## 17. Open Questions

1. **Multi-hand variant:** A 3/5/10-hand simultaneous deal is popular in real cabinets — out of scope for MVP but worth specifying if demand exists. [Estimated: add in Phase 2 post-MVP]
2. **Optimal strategy overlay:** Highlighting the statistically correct cards to hold adds educational value but risks removing challenge — toggle-able feature. [Estimated: toggle in settings, default off]
3. **Exact animation timing preferences:** Card flip duration (100ms vs 300ms vs 500ms) affects feel significantly — tune through playtesting. [Estimated: 200ms per card, staggered 50ms]
