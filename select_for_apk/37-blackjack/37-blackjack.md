# Blackjack (21) — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Blackjack (also called "21") is the world's most widely played casino card game. Players compete directly against the dealer, aiming to build a hand value closer to 21 than the dealer without exceeding it. The game blends pure chance with meaningful skill (when to hit, stand, split, double, take insurance), creating genuine tension in every hand. The core appeal is that optimal basic strategy reduces the house edge to under 0.5%, making it one of the fairest casino games — and one of the most satisfying to learn.

**Quick facts:**
- Developer/Origin: Traditional casino card game, no single developer [Confirmed]
- Platforms: Physical casinos worldwide; every major online casino; countless digital clones on Web/iOS/Android/PC [Confirmed]
- Genre: Card game / Casino gambling simulation
- Session length: 2–30 minutes (one round ~60 seconds)
- Play style: Active, turn-based, single-player vs. AI dealer
- Age/Content rating: ESRB T (Teen) or M (Mature) for gambling themes; PEGI 18 in most jurisdictions [Confirmed]
- Monetization model (clone): Free-to-play with virtual chips; no real money

---

## 2. Core Loops

- **30-second loop:** Place a bet → receive two cards face-up (dealer gets one face-up, one face-down) → decide Hit/Stand/Double/Split → dealer reveals hole card and plays → win/lose/push chips → start next round.
- **Session loop:** Enter with a bankroll → play multiple hands, growing or shrinking the stack → chase a profit target or stop-loss limit → bank stats (hands won, blackjacks hit, biggest pot) before leaving.
- **Meta loop:** Accumulate chips across sessions (localStorage persistence) → unlock higher table minimums/maximums → track lifetime stats (win rate, blackjacks, biggest win streak) → chase achievements (e.g., "win 5 in a row", "hit blackjack 3 times in one session").

---

## 3. Mechanics, Controls & Game States

### Core Rules [Confirmed]
- Standard 52-card deck × 6 decks (shoe); reshuffled when ~25% of cards remain.
- Card values: 2–10 = face value; J/Q/K = 10; Ace = 1 or 11 (soft/hard hand logic).
- **Soft hand:** Any hand with an Ace counted as 11 without busting (e.g., A+6 = soft 17).
- **Hard hand:** Any hand where no Ace is counted as 11, or where doing so would bust.
- Goal: Beat dealer's hand value without exceeding 21.

### Player Actions
| Action | Condition | Effect |
|--------|-----------|--------|
| **Hit** | Any non-bust hand | Draw one card; recalculate total |
| **Stand** | Any hand | End player turn |
| **Double Down** | First two cards only (some tables allow after split) | Double bet, draw exactly one card, stand |
| **Split** | First two cards are a pair (same face value) | Split into two separate hands; each gets one more card; bet duplicated per hand |
| **Insurance** | Dealer's up-card is Ace | Side bet = half main bet; pays 2:1 if dealer has blackjack; loses otherwise |
| **Surrender** | First two cards, before any other action (optional rule) | Forfeit half the bet, retrieve the other half |

### Split Rules [Confirmed]
- Only initial two cards with equal face value may be split.
- Each new hand gets one additional card dealt.
- Up to 3 additional splits allowed (max 4 hands total) — except Aces.
- Split Aces: each Ace gets exactly one additional card; no further hits; no re-splitting Aces.
- A 10-value card on a split Ace = 21, NOT blackjack (no 3:2 bonus, just 1:1 win).

### Dealer Rules [Confirmed]
- Dealer reveals hole card after player(s) finish.
- Dealer **must hit** on hard 16 or less.
- Dealer **stands on hard 17 or more**.
- Dealer **stands on soft 17** (this implementation; house rule variant: hit soft 17 for slightly higher house edge).
- Dealer plays automatically — no decisions.

### Blackjack (Natural) [Confirmed]
- Two-card hand totaling 21 (Ace + 10-value card).
- Pays **3:2** on the initial bet (e.g., bet $20 → win $30).
- If dealer also has blackjack → **push** (tie; bet returned).
- Blackjack beats a regular 21 reached via multiple cards.

### Win/Lose/Push Conditions
| Outcome | Result |
|---------|--------|
| Player total > Dealer total (both ≤ 21) | Win 1:1 |
| Player blackjack, dealer no blackjack | Win 3:2 |
| Player total > 21 (bust) | Lose bet immediately |
| Dealer busts, player does not | Win 1:1 |
| Player total = Dealer total | Push (bet returned) |
| Dealer total > Player total (no bust) | Lose bet |
| Insurance wins (dealer blackjack) | Win 2:1 on insurance side bet |

### Game States (FSM)
1. **IDLE** — waiting for bet; Deal button disabled until bet placed.
2. **DEAL** — cards animate out; check for player/dealer blackjack.
3. **INSURANCE_OFFER** — shown only if dealer Ace up-card; player accepts/declines.
4. **PLAYER_TURN** — Hit/Stand/Double/Split buttons active; illegal actions grayed.
5. **DEALER_TURN** — dealer auto-plays hole card flip + hits.
6. **RESOLVE** — calculate winner(s), animate chip gain/loss, show result banner.
7. **BROKE** — player bankroll = 0; offer free chip reset.

### Game Modes
- **Single mode only:** One player vs. AI dealer. No PvP, no tournament mode in MVP.
- **Optional:** Settings for number of decks (1/2/4/6/8), hit-soft-17 toggle, surrender toggle.

### Input Scheme
- Desktop: mouse click on chip buttons, action buttons, card areas.
- Mobile: tap (touch events); large 44×44px+ touch targets.
- Orientation: portrait-first; also playable landscape.

### Feedback Systems
- Card dealing animation (slide-in).
- Chip stack visual change on bet/win/lose.
- Sound: card flip, chip clink, win chime, bust buzz, blackjack fanfare.
- Hand total display updates live.
- Bust/Win/Push/Blackjack banner on resolution.
- Illegal action buttons disabled + visually grayed.

---

## 4. Progression

### Bankroll Progression [Estimated]
| Level | Unlocks | Starting Bankroll |
|-------|---------|------------------|
| Beginner Table | $1–$25 bets | $500 (starting) |
| Standard Table | $5–$100 bets | $1,000 (reached) |
| High Roller Table | $25–$500 bets | $5,000 (reached) |

- Progression is purely bankroll-gated — no XP, no levels.
- Achievements track milestones (win streak, total blackjacks, profit milestones).
- Broke reset gives $500 free chips to restart.

### Stats Tracked (localStorage)
- Hands played, hands won, hands lost, pushes
- Blackjacks dealt to player
- Best win streak, current win streak
- Biggest single hand win
- Total chips won/lost lifetime

---

## 5. Economy & RNG *(tables)*

### Currencies
| Currency | Type | Earned From | Spent On |
|----------|------|-------------|---------|
| Chips ($) | Soft (virtual only) | Winning hands, free reset | Bets |

- No premium/hard currency. No real money. Chips are cosmetic/progression only.

### Bet Sizing
| Table | Min Bet | Max Bet | Recommended Starting Bet |
|-------|---------|---------|--------------------------|
| Beginner | $1 | $25 | $5 |
| Standard | $5 | $100 | $25 |
| High Roller | $25 | $500 | $100 |

### Payouts [Confirmed]
| Event | Payout |
|-------|--------|
| Standard win | 1:1 (even money) |
| Blackjack | 3:2 |
| Insurance win | 2:1 (on insurance bet) |
| Push | 0 (bet returned) |
| Double Down win | 1:1 on doubled bet |
| Split hand win | 1:1 per hand |

### House Edge (Theoretical) [Confirmed]
- 6-deck, dealer stands soft 17, double on any two cards, split up to 4 hands: ~0.40–0.50% house edge with basic strategy.
- Without basic strategy: ~2–3% house edge.

### RNG
- JavaScript `Math.random()` used for Fisher-Yates shuffle of all 6 decks (312 cards).
- Reshuffled when fewer than ~78 cards remain (~25% cut point). [Estimated]
- No weighted RNG — pure uniform shuffle. [Confirmed for fair implementation]
- Card draw is sequential from shuffled shoe — not re-randomized per card.

### Chip Earn/Lose Balance [Estimated]
- Starting bankroll: $500
- Expected loss rate at ~0.5% house edge: ~$0.025 per $5 bet
- At $5 bets, bankroll lasts ~10,000 hands statistically (very generous for casual play)
- Free reset at $0 keeps player engaged indefinitely

---

## 6. Content Inventory *(counts + lists)*

### Cards
- 52 unique cards × 6 decks = 312 cards in shoe [Confirmed]
- 4 suits: ♠ Spades, ♥ Hearts, ♦ Diamonds, ♣ Clubs
- 13 ranks per suit: A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K

### Tables / Environments
- 1 green felt table (main play area)
- Visual variant chips (white $1, red $5, green $25, black $100, purple $500) [Estimated]

### UI Screens
- Main game table (single screen, persistent)
- Settings modal
- Stats modal
- How-to-play modal
- Broke screen (overlay)
- Result banner (overlay)

### Sound Effects (WebAudio generated)
- Card deal/flip
- Chip place
- Chip collect (win)
- Bust sound
- Blackjack fanfare
- Push/tie sound
- Button click

### Achievements [Estimated, 8 total]
1. First Win — Win your first hand
2. Blackjack! — Hit a natural blackjack
3. Lucky Streak — Win 5 hands in a row
4. High Roller — Place a $100+ bet
5. Split Decision — Successfully split a pair
6. Double Trouble — Win a doubled-down hand
7. Comeback Kid — Recover from under $100 to $500+
8. Millionaire (virtual) — Accumulate $10,000+

---

## 7. Theme, Narrative & Tone

### Setting
- Classic Las Vegas casino table. Rich green felt, warm gold accents, dark wood trim.
- No narrative or story — pure mechanical game.

### Visual Theme
- Dark casino ambiance: deep green (#1a5c2a) felt, cream/gold card faces, red/black pip colors.
- Card backs: solid dark with subtle pattern.
- Chips: colored circles with denomination text.

### Tone
- Sophisticated but approachable. Tense during play, celebratory on wins, sympathetic on busts.
- No characters or dialogue — the dealer is a faceless entity (no avatar needed in MVP).
- Writing style: concise UI labels only ("Hit", "Stand", "Blackjack! You win $30").

### Sound Tone
- Casino ambient (optional low background hum).
- Crisp, satisfying card sounds; coin/chip clinks.
- Win fanfare is rewarding but not garish.

---

## 8. Meta & Social Systems

### Stats (localStorage persisted)
- Lifetime chips won/lost, hands played, win rate, blackjack count.
- Displayed in Stats modal.

### Achievements
- 8 achievements tracked in localStorage (see Section 6).
- No online leaderboards in MVP (single-player offline game).

### Daily Hook [Estimated]
- "Daily bonus": +$100 chips on first open each calendar day.
- Streak tracker: consecutive days played (cosmetic).

### No Social / Multiplayer
- Offline single-player. No PvP, guilds, leaderboards, or sharing in MVP.
- Share button (optional): copy text score to clipboard.

### Live-Ops Cadence
- None needed — game is self-contained with no content updates required.
- Optional: periodic rule variants (hit-soft-17 toggle, surrender, etc.) as settings.

---

## 9. UI / UX & Screen Map

### Screens
| Screen | Purpose |
|--------|---------|
| Main Table | Core gameplay — always visible |
| Settings Modal | Deck count, soft-17 rule, surrender toggle, mute, stats reset |
| Stats Modal | Lifetime stats, achievements |
| How-to-Play Modal | Rules summary, payouts chart |
| Broke Overlay | "You're out of chips!" + free reset button |
| Result Banner | Overlay showing outcome + chip change |

### Gameplay HUD Elements
- **Top bar:** Bankroll display ($), Mute button, Settings icon, Stats icon, Help icon
- **Dealer area:** "Dealer" label, cards row, hand value (hidden until reveal), "Dealer Busts!" etc.
- **Center:** Result banner (animates in/out)
- **Player area:** "You" label, cards row, hand value (live), split hand cards if applicable
- **Bet area:** Current bet display, chip buttons ($1/$5/$25/$100/$500), Clear Bet button
- **Action buttons:** Deal | Hit | Stand | Double | Split | Insurance (context-sensitive visibility)

### Navigation Flow
```
Main Table
  ├── [⚙] → Settings Modal → close
  ├── [📊] → Stats Modal → close
  ├── [?] → How-to-Play Modal → close
  └── [Bankroll = 0] → Broke Overlay → Reset Chips → Main Table
```

### Onboarding / Tutorial (First Launch)
1. Welcome tooltip: "Welcome to Blackjack! You start with $500 in chips."
2. Arrow points to chip buttons: "Tap chips to place your bet."
3. Player taps a chip → bet area updates → tooltip: "Great! Now tap Deal."
4. Player taps Deal → cards animate in → tooltip: "Your cards are dealt. Total shown below."
5. Tooltip explains Hit/Stand: "Hit = take a card. Stand = keep your hand."
6. Player picks an action → round resolves → tooltip: "Round over! Tap chips to bet again."
7. Tutorial dismissed; "Show tutorial again" option in Settings.

### Settings Menu Contents
- Number of decks: 1 / 2 / 4 / 6 / 8 (default: 6)
- Dealer hits soft 17: ON/OFF (default: OFF — stands on soft 17)
- Allow surrender: ON/OFF (default: OFF)
- Sound: ON/OFF (default: ON)
- Animation speed: Fast/Normal/Slow
- Reset stats (confirmation prompt)
- Reset chips (confirmation prompt)
- How to play

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- 2D; flat top-down view of casino table.
- No real camera — fixed static layout.
- Portrait-primary; landscape adapts via CSS flexbox.

### Art Style
- Flat / semi-skeuomorphic. Dark felt texture (CSS gradient), card white with colored pips.
- Cards: white rectangle, rounded corners, rank + suit in corners, large centered pip cluster.
- Suits rendered with Unicode: ♠ ♥ ♦ ♣ (black/red coloring).
- No external images required.

### Color Palette
| Element | Color |
|---------|-------|
| Table felt | #1a5c2a (dark green) |
| Table border/rail | #8B5E3C (casino wood brown) |
| Card face | #FFFFFF |
| Card back | #1a237e (dark blue with pattern) |
| Spades/Clubs | #1a1a1a |
| Hearts/Diamonds | #CC0000 |
| Chip $1 | #FFFFFF with blue text |
| Chip $5 | #CC0000 |
| Chip $25 | #1a8c1a |
| Chip $100 | #1a1a1a |
| Chip $500 | #6a0dad |
| Gold accent | #D4AF37 |
| UI text on felt | #F5DEB3 (wheat/cream) |

### Animation
- Cards deal with CSS transition (slide from deck position to hand position, ~200ms).
- Card flip: CSS 3D rotateY (hole card reveal).
- Chip toss: brief upward arc CSS keyframe.
- Win: green glow pulse on player hand; chips fly from dealer area.
- Bust: red flash on player cards.
- Blackjack: gold sparkle effect (CSS keyframe).

### VFX
- Screen shake (subtle) on bust.
- Particle burst (CSS) on blackjack win.
- Running total badge pulses on value change.

### Audio (WebAudio API, synthesized — no files)
- Card deal: brief noise burst (swoosh).
- Chip place: short tick.
- Win: ascending tone sequence.
- Bust: descending tone + low thud.
- Blackjack: 3-note ascending fanfare.
- Push: neutral two-note ding.
- All audio gated by mute toggle.

---

## 11. Monetization

**This clone uses virtual chips only — no real money, no ads, no IAP.** [Design decision]

| Monetization | Implementation |
|-------------|----------------|
| Ads | None |
| IAP | None |
| Subscriptions | None |
| Loot boxes/Gacha | None |

- Free reset (always available) ensures the player is never truly blocked.
- No consent/ATT/CMP required (no tracking, no ads, no real money).
- Age-gating: display "For entertainment only — no real money" disclaimer in footer.

---

## 12. Retention Hooks

### Daily Bonus [Estimated]
- +$100 chips on first session of each new calendar day (localStorage date check).
- Bonus announced with a modal: "Daily Bonus! +$100 chips."

### Win Streak Tracker
- Visible streak counter on HUD; achieving 3/5/10 in a row triggers celebratory animation.

### Achievement System
- 8 achievements (Section 6); each unlocks a badge shown in Stats modal.
- Newly unlocked achievements toast-notify during play.

### Offline/Idle Earnings
- None — Blackjack is an active game; no idle mechanic. [Confirmed: not applicable]

### Push Notifications
- None (web game, no service worker push in MVP).

### Energy/Lives System
- None. Play as many hands as bankroll allows; free reset on broke.

### FOMO Mechanics
- None. Timeless rules; no limited-time events needed.

---

## 13. Localization & Accessibility

### Languages
- English only in MVP. [Estimated — easy to extend]
- No RTL languages in MVP.

### Accessibility
- Font size ≥ 16px for all text; card rank/suit large and clearly readable.
- Color-blind mode: suits include both color AND shape (Unicode symbols are inherently distinct).
- WCAG AA contrast ratios on all text vs. background.
- Keyboard navigation: Tab to cycle buttons, Enter/Space to activate (desktop).
- Reduced-motion setting: disables card animations for users with motion sensitivity.

### Age/Content Rating
- ESRB: T (Teen) — simulated gambling. [Confirmed for casino simulation genre]
- PEGI 12–18 depending on jurisdiction.
- Disclaimer: "For entertainment purposes only. No real money is involved." [Required best practice]
- No COPPA concern if disclaimer and no data collection is implemented.

### Regional Differences
- No regional rule variants in MVP (optional: European blackjack no-hole-card variant as setting).

---

## 14. Technical Structure

### Engine / Framework
- Vanilla JavaScript + HTML5 + CSS3. No framework, no build step. [Design decision]
- Single self-contained .html file with inline `<style>` and `<script>`.

### Platform
- Web (desktop + mobile). Runs offline after first download. Double-click to open.
- No server, no network requests, no external CDN.

### Save System
- localStorage keys:
  - `bj_bankroll` — current chip balance
  - `bj_stats` — JSON object {handsPlayed, handsWon, handsLost, pushes, blackjacks, bestStreak, currentStreak, biggestWin, lifetimeWon, lifetimeLost}
  - `bj_achievements` — JSON array of unlocked achievement IDs
  - `bj_settings` — JSON object {decks, hitSoft17, surrender, sound, animSpeed}
  - `bj_lastBonus` — ISO date string for daily bonus tracking

### Accounts / Auth
- None. Guest-only, no login. All data local.

### Cross-Device Sync
- None in MVP (localStorage is device-specific).

### Multiplayer / Netcode
- None. Single-player vs. AI. [N/A]

### Anti-Cheat / Server Authority
- N/A — client-side only; virtual chips have no real value.

### Backend / Analytics
- None. Fully offline.

### Third-Party SDKs
- None.

### Performance
- Target: 60fps animations on mid-range mobile (CSS transitions, minimal DOM changes).
- Estimated file size: ~30–50KB (no images, no fonts, inline only).

---

## 15. Pacing & Difficulty

### Early Game (First 20 hands)
- Tutorial guides bet placement and basic actions.
- Low table minimums ($1) allow exploration without pressure.
- "Lucky" early distribution helps new players win early hands.
- Key milestone: first blackjack hit (fanfare + achievement).

### Mid Game (Hands 21–200)
- Player learns when split/double opportunities arise.
- Bankroll variance is felt — occasional bad streaks.
- Achievements reward exploration of all mechanics.
- Key milestone: first successful split; first double-down win.

### Late / Ongoing Game (200+ hands)
- Player is using the same loop; depth comes from bankroll management.
- High Roller table unlocked at $5,000 — bigger swings, higher drama.
- Lifetime stats and streaks provide long-term tracking.
- No hard "end" — infinite play loop.

### Difficulty Curve
- The game itself does not increase in difficulty — dealer always plays fixed rules.
- Perceived difficulty increases via bet sizing — high-stake hands feel much more tense.
- "Difficulty" is entirely the player's own risk management.

### Common Churn Points [Estimated from genre]
- Going broke early due to oversized bets (mitigated by free reset).
- Confusion about split/double rules (mitigated by tutorial + how-to modal).
- Frustration with dealer drawing to 21 repeatedly (variance; mitigated by streaks/stats showing long-run fairness).

---

## 16. Clone Build Plan

### MVP Feature Set
- [x] 6-deck shoe with Fisher-Yates shuffle
- [x] Bet with chip buttons ($1/$5/$25/$100/$500)
- [x] Deal 2 cards to player + 2 to dealer (one hole card)
- [x] Hit / Stand actions
- [x] Double Down (first two cards)
- [x] Split (pairs, up to 4 hands; Ace special rules)
- [x] Insurance offer (when dealer Ace)
- [x] Soft/hard Ace value calculation
- [x] Blackjack detection + 3:2 payout
- [x] Dealer auto-play (hit < 17, stand ≥ 17)
- [x] Win/Lose/Push resolution per hand
- [x] Bankroll persistence (localStorage)
- [x] Broke reset
- [x] Mute toggle + WebAudio SFX
- [x] Mobile-responsive layout
- [x] How-to-play modal
- [x] Stats tracking

### Full Feature Set (Post-MVP)
- [ ] Surrender option
- [ ] Hit soft 17 rule toggle
- [ ] Deck count settings (1/2/4/6/8)
- [ ] Daily bonus system
- [ ] 8 achievements
- [ ] Animation speed setting
- [ ] European blackjack variant
- [ ] Side bets (Perfect Pairs, 21+3)
- [ ] Card counting training mode

### Phased Roadmap

**Phase 1 — Core Engine (Day 1–2)**
- Card/deck model, shuffle algorithm, hand value calculator (soft/hard aces)
- Blackjack detection, dealer auto-play logic
- Win/push/lose resolution + payout math

**Phase 2 — Betting + Actions (Day 2–3)**
- Chip buttons, bet display, bankroll tracking
- Hit/Stand/Double/Split/Insurance state machine
- Illegal action gating (disable buttons per game state)

**Phase 3 — UI + Rendering (Day 3–4)**
- CSS card rendering (Unicode suits, colored pips)
- Table layout (dealer area, player area, bet area, action buttons)
- Result banner, hand totals, HUD

**Phase 4 — Persistence + Polish (Day 4–5)**
- localStorage save/load
- WebAudio SFX
- Deal/flip animations (CSS transitions)
- Responsive mobile layout

**Phase 5 — Meta + QA (Day 5–6)**
- Stats modal, settings modal, how-to modal
- Daily bonus, achievements
- Full playthrough QA: ace edge cases, split flow, double after split, blackjack vs. blackjack push

### Recommended Tech Stack
- HTML5 + CSS3 + Vanilla JavaScript (ES6+)
- No libraries, no build tools
- localStorage for persistence
- Web Audio API for sound
- CSS Flexbox/Grid for layout
- CSS transitions/keyframes for animation

### Required Asset List
- **Art:** All CSS/Unicode — no image files needed
- **Audio:** All WebAudio synthesized — no audio files needed
- **Data:** Card ranks/suits as JS constants; payout tables as JS object literals

### Hardest Parts / Risks
1. **Ace soft/hard value recalculation:** Must correctly re-evaluate all aces in hand when adding a new card would bust. Off-by-one errors here break the whole game.
2. **Split hand state management:** Multiple simultaneous hands, each needing independent hit/stand/double state, proper active-hand tracking, and correct payout per hand.
3. **Blackjack detection edge case:** 10 on a split Ace ≠ blackjack. Must distinguish correctly.
4. **Dealer hole card reveal timing:** UI must hide value until reveal; value must be correct before dealer plays.
5. **Button disable logic:** All 6 actions have distinct enable conditions; illegal moves breaking game state is the most common QA failure.

---

## 17. Open Questions

1. **Surrender rule:** Standard (early surrender, before insurance?) or late surrender only? [Design choice — omit in MVP, add as toggle]
2. **Double after split:** Allow doubling on split hands? [Common rule, adds ~0.14% player edge — include as setting, default ON]
3. **Re-split Aces:** Allow? [Rare in casinos; default OFF, optional setting]
4. **Side bets (Perfect Pairs, 21+3):** Out of MVP scope — worth adding in v2 for revenue hooks in a real casino app.
5. **Localization:** If releasing in regulated markets, real-money display and gambling disclaimers may require jurisdiction-specific text.

---

*Blueprint complete. All 17 sections filled. No TBDs outside Open Questions. Sources: Confirmed rules from bicyclecards.com, casino.org, playusa.com; Estimated values based on standard casino industry norms and genre analysis.*
