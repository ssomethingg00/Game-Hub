# Higher-Lower Card Game — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not any original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Higher-Lower (also known as Hi-Lo) is a pure prediction card game: one card is shown face-up and the player guesses whether the next card from the deck will be higher or lower in rank. Correct guesses extend a streak and multiply the score; a wrong guess ends the round (or costs a life). A chip-betting/cash-out layer adds gambling-style tension. The appeal is pure, fast decision-making with escalating risk/reward — zero skill floor, yet meaningful probability awareness at higher streaks.

**Quick facts:**
- Genre: Card / Prediction / Casual Gambling Sim [Estimated]
- Platforms (clone target): Web (HTML5), mobile-first responsive
- Age/content rating: PEGI 12 / ESRB Teen (simulated gambling) [Estimated]
- Monetization model (clone): Free-to-play, optional rewarded ad for chip refill [Estimated]
- Session length: 2–10 minutes [Estimated]
- Play style: Active, single-player, portrait-friendly

---

## 2. Core Loops

- **30-second loop:** A card is shown → player taps Higher or Lower → next card is revealed with a flip animation → result shown (correct/wrong) → streak and score update → next card set as current → repeat.
- **Session loop:** Player starts with a chip stack → places a bet → builds a streak as long as possible → optionally cashes out before risking → either a wrong guess ends the round or the deck runs out → round summary shows streak/score/chips won → new round begins.
- **Meta loop:** Player checks best streak and high score (persisted to localStorage) → tries to beat personal best → improves probability intuition → chases the next milestone multiplier.

---

## 3. Mechanics, Controls & Game States

### Card Rank Ordering
Ranks lowest to highest: **2 3 4 5 6 7 8 9 10 J Q K A**
- Ace is **HIGH** (value 14). [Confirmed — standard casino Hi-Lo rule]
- Suits are **irrelevant** to ranking. [Confirmed]
- **Tie rule:** If the next card's rank equals the current card's rank, it is a **Push (tie) — the player does NOT lose; streak is maintained but no point bonus is awarded for that step.** The tied card becomes the new current card and play continues. [Estimated — player-friendly variant chosen to reduce frustration; clearly stated in HUD]

### Core Verbs
- **Guess Higher** — predict next card rank > current rank.
- **Guess Lower** — predict next card rank < current rank.
- **Cash Out** — lock in current winnings mid-streak (available after ≥ 2 correct in a row).
- **Place Bet** — choose chip wager before each round begins.

### Game Modes
| Mode | Description |
|---|---|
| **Classic (Streak)** | Single life; wrong guess ends round; best streak tracked. |
| **Lives Mode** | 3 lives; wrong guess costs a life; run ends at 0 lives. |
| **Bet & Cash Out** | Chip wagering layer on top of Classic; multiplier rides on streak; cash out preserves winnings. |

All three modes share the same card engine; mode is selectable from the start screen.

### Input Scheme
- **Mobile + desktop:** Large tap/click buttons (Higher / Lower / Cash Out / Bet controls).
- Portrait orientation primary; landscape supported with side-by-side card layout.
- Buttons disabled during card-flip reveal animation (~600 ms) to prevent mis-taps.

### Win / Lose / Fail Conditions
| Condition | Outcome |
|---|---|
| Correct guess | Streak +1, score increases, multiplier rises |
| Wrong guess | Round over (Classic/Bet) or life lost (Lives Mode) |
| Tie (equal rank) | Push — streak held, no bonus, play continues |
| Deck runs low (< 10 cards) | Deck reshuffled silently; card count indicator updates |
| Cash Out triggered | Round ends voluntarily; full multiplied winnings banked |
| 0 lives (Lives Mode) | Game over |

### Difficulty / AI
- No AI opponent. Difficulty arises naturally from probability: extreme-rank cards (2 or A) are easy calls; mid-rank cards (7, 8) are near-50/50. [Confirmed — inherent to card mechanics]
- No explicit difficulty mode in MVP; tie-rule display is the only assist.

### Feedback Systems
- Visual: card flip animation, green glow (correct), red shake (wrong), gold pulse (cash out).
- Audio: card flip sfx, correct ding, wrong buzz, cash out chime, streak milestone fanfare (WebAudio, all generated).
- HUD streak counter animates up/down on each result.
- Score pops with a floating "+N" particle on correct guess.

---

## 4. Progression

### Within a Round
- Streak drives a **multiplier ladder**: each correct guess advances one step.
- Multiplier ladder [Estimated based on casino variant research]:

| Streak | Multiplier |
|---|---|
| 1 | 1× |
| 2 | 1.5× |
| 3 | 2× |
| 4 | 3× |
| 5 | 5× |
| 6 | 8× |
| 7 | 13× |
| 8 | 20× |
| 9 | 30× |
| 10+ | 50× (cap) |

### Session Progression
- Chips accumulate across rounds in Bet mode.
- Best Streak and Best Score persist in localStorage.

### Unlocks / Upgrades
- MVP: none (pure gameplay focus). [Estimated — appropriate for genre]
- Future: unlockable card back themes, avatar frames at streak milestones.

### Gating
- No hard paywalls. Chip depletion gates continued betting (must start new round at minimum bet).
- Lives refill each new game session (or via optional rewarded ad in full version).

---

## 5. Economy & RNG *(tables)*

### Currencies

| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| Chips | Soft | Winning rounds, daily login bonus | Placing bets each round |
| Score Points | Session score | Correct guesses × multiplier | N/A (display only) |

### Chip Economy [Estimated]

| Starting Chips | Min Bet | Max Bet | Daily Bonus | Broke Fallback |
|---|---|---|---|---|
| 1,000 | 10 | 500 | +200 | Reset to 200 (mercy floor) |

### Payout Formula (Bet Mode)
```
payout = bet × multiplier[streak]
net_gain = payout − bet
```
Cash-out at any streak step: player receives `payout` and round ends.

### Score Formula (Classic Mode)
```
score_gain = 100 × multiplier[streak]
```
Each correct guess at streak N adds `100 × multiplier[N]` to session score.

### RNG
- Standard 52-card deck, Fisher-Yates shuffle. [Confirmed — deterministic shuffle, no weighted RNG]
- No gacha / loot tables. [Confirmed — pure card probability]
- Probability reference [Confirmed — mathematics]:
  - If current card is rank R (2–14), P(higher) = (14−R)×4 / remaining_cards, P(lower) = (R−2)×4 / remaining_cards.

### Deck Reshuffle Trigger [Estimated]
- When fewer than 10 cards remain in the draw pile, the discard pile is reshuffled into a new deck. A subtle "Reshuffled" toast appears.

---

## 6. Content Inventory *(counts + lists)*

| Category | Count | Notes |
|---|---|---|
| Deck | 52 cards | 4 suits × 13 ranks, standard |
| Suits | 4 | ♠ ♥ ♦ ♣ (Unicode/CSS only) |
| Ranks | 13 | 2–10, J, Q, K, A |
| Card Backs | 1 (MVP) | CSS gradient design, no images |
| Game Modes | 3 | Classic, Lives, Bet & Cash Out |
| SFX | 6 | Flip, correct, wrong, cash-out, streak milestone, reshuffle |
| Screens | 5 | Start, Mode Select, Gameplay, Round Summary, Game Over |
| HUD Elements | 7 | Current card, Next card zone, Streak, Score, Best, Lives/Chips, Multiplier badge |

---

## 7. Theme, Narrative & Tone

- **Setting:** Stylized casino / card table — deep green baize, gold trim, subtle felt texture via CSS. [Estimated]
- **Premise:** No story. The appeal is pure mechanical tension of the guess. [Confirmed — genre norm]
- **Narrative delivery:** None; all communication is HUD and result banners.
- **Tone:** Sleek, calm confidence — not cartoonish, not gritty. Satisfying "pop" juice without being juvenile.
- **Color palette:** Deep green (#1a4a2e), gold (#f0c040), cream (#fdf6e3), red (#cc3333), dark navy (#0d1b2a).
- **No licensed IP.** Original branding as "HIGHLOW" or similar.

---

## 8. Meta & Social Systems

- **Best Streak** and **Best Score** persisted to localStorage. [Confirmed — standard for this genre]
- **Chip balance** persisted between sessions (Bet mode). [Estimated]
- **Daily login bonus:** +200 chips on first daily visit (localStorage timestamp check). [Estimated]
- **No multiplayer, leaderboards, guilds, or social sharing in MVP.** [Estimated — appropriate scope]
- **Live-ops cadence:** None in MVP; future potential: weekly streak challenges, daily deck seeds for shared daily challenge.
- **Achievements (future):** "First 5-streak", "First 10-streak", "Cashed out with 8× multiplier", etc.

---

## 9. UI / UX & Screen Map

### Screens

| Screen | Purpose |
|---|---|
| **Start / Title** | Logo, "New Game" CTA, "How to Play" toggle, best streak/score display, mute button |
| **Mode Select** | Classic / Lives / Bet — tap to select, brief description per mode |
| **Gameplay** | Core loop: current card, next-card zone, Higher/Lower buttons, HUD |
| **Round Summary** | Streak achieved, score earned, chips won/lost, "Play Again" / "Menu" |
| **Game Over** | Final stats, best streak highlighted if new record, replay/menu CTA |

### Settings / Options (accessible via gear icon on Start screen)
- Sound On/Off (mute toggle)
- Tie Rule reminder (static text)
- Reset Best Score / Chips (with confirmation)
- About / Credits

### Gameplay HUD Elements
- Current card (large, face-up, center-left)
- Next card zone (face-down card placeholder, center-right, flips on reveal)
- Streak counter (prominent, top-center)
- Multiplier badge (below streak)
- Score (top-right)
- Best score (sub-text under score)
- Lives indicators (♥♥♥ — Lives mode) or Chip balance + bet selector (Bet mode)
- Mute toggle (corner)
- Cash Out button (appears at streak ≥ 2, Bet mode)

### Navigation Flow
```
Title → Mode Select → Gameplay → Round Summary → [Gameplay loop] OR → Title
                                               → Game Over → Title
```

### Onboarding / Tutorial (first-time play)
1. Animated tooltip points to current card: "This is your current card."
2. Tooltip points to Higher button: "Will the next card be HIGHER or LOWER?"
3. Player taps Higher or Lower (forced interaction, not skippable).
4. Card flips — result shown — tooltip: "Correct! Your streak grows."
5. Multiplier badge pulses — tooltip: "Streak = bigger multiplier = more points."
6. Tie rule card appears: "Same rank? It's a Push — you keep your streak!"
7. Tutorial complete; normal gameplay resumes. Skippable after step 2 via "Skip" button.

---

## 10. Art, Audio, Camera & Feel

- **Dimension:** 2D, flat/card-table aesthetic. [Estimated]
- **Camera/perspective:** Static top-down view of card table. Portrait layout primary. [Estimated]
- **Art style:** Flat design with subtle depth (card shadows, felt texture via CSS radial gradients). Unicode suit symbols (♠♥♦♣) styled with CSS color and font-size — no raster images. [Confirmed — hard requirement]
- **Card design:** Rounded rectangle, white face, rank + suit top-left and bottom-right, large centered suit pip. Red for ♥♦, black/dark for ♠♣. Back: CSS diagonal stripe gradient in navy/gold.
- **Animation:** CSS 3D flip (rotateY 0→90° then swap face/back → 90°→0°), ~600 ms, ease-in-out. Correct guess: green border glow pulse. Wrong guess: red shake keyframe. Cash out: gold shimmer.
- **VFX:** Floating score pop (+N text rises and fades). Confetti burst CSS animation on new best streak.
- **SFX (WebAudio, generated — no files):**
  - Card flip: short noise burst with fast envelope
  - Correct: ascending two-note chime (E5→G5)
  - Wrong: descending buzz (low square wave)
  - Cash out: triple ascending chime
  - Streak milestone (every 5): short fanfare
  - Reshuffle: soft whoosh
- **Music:** None in MVP (avoids fatigue in short sessions). [Estimated]
- **Mute toggle:** Persisted to localStorage.

---

## 11. Monetization

**MVP (clone) monetization: None / Optional minimal.** [Estimated — pure gameplay focus]

| Ad Type | Placement | Frequency |
|---|---|---|
| Rewarded video (optional) | "Watch ad to refill chips to 200" — only shown when chips < min bet | On player request only |
| Interstitial | Not used in MVP | N/A |
| Banner | Not used in MVP | N/A |

### IAP (Future / Optional)
| Product | Price | Value |
|---|---|---|
| Remove Ads | $0.99 | Removes rewarded ad prompt |
| Chip Pack (1,000) | $0.99 | Soft currency top-up |
| Chip Pack (5,000) | $2.99 | Better value |

**No gacha / loot boxes / random monetization.** [Confirmed — not applicable to this genre]

### Consent / Privacy
- No user tracking in MVP (no ad SDKs, no analytics).
- If ads added later: GDPR CMP consent popup required before ad load; iOS ATT prompt required before IDFA access.
- Age-gate: if simulated gambling chips are present, display age confirmation on first launch (PEGI 12 / ESRB Teen target).

**Aggressiveness:** Very low. Rewarded ad is purely optional and only surfaces when the player is stuck. [Estimated]

---

## 12. Retention Hooks

- **Best Streak / Best Score display:** On title screen and end screen — persistent badge of achievement. [Confirmed — standard]
- **Daily chip bonus:** +200 chips on first daily session (localStorage date check). [Estimated]
- **Escalating multiplier FOMO:** Multiplier ladder visible at all times; reaching a new tier creates urgency to cash out vs. push on. [Confirmed — core tension mechanic]
- **Offline / idle earnings:** None — this is an active prediction game. [Confirmed — N/A]
- **Push notifications:** None in MVP (web game; no service worker). [Estimated]
- **Lives system:** 3-life mode creates "one more try" loops after mistakes. [Estimated]
- **Streak milestone celebrations:** Confetti + fanfare at streaks 5, 10, 15 create dopamine peaks.

---

## 13. Localization & Accessibility

**Languages:** English only in MVP. [Estimated]

**Accessibility:**
- All text scalable (rem units, no fixed px font sizes on content). [Estimated]
- Colorblind: red/green result feedback supplemented by ✓/✗ icon labels, not color alone. [Estimated]
- Tap target size: Higher/Lower buttons minimum 56×56 px (WCAG 2.5.5). [Estimated]
- Keyboard navigation: Higher = ArrowUp, Lower = ArrowDown, Cash Out = Space (desktop). [Estimated]
- Screen orientation: works portrait and landscape. [Estimated]
- No voice/audio required for any game-critical info (all outcomes shown visually). [Confirmed]

**Age/Content rating:** PEGI 12 / ESRB Teen — simulated gambling (no real money). [Estimated]
**COPPA compliance:** No personal data collected; no account creation; no chat. MVP is inherently compliant. [Estimated]
**Regional differences:** None in MVP. [Estimated]

---

## 14. Technical Structure

- **Engine/framework:** Vanilla HTML5 + CSS3 + ES6 JavaScript. No build step, no dependencies. [Confirmed — hard requirement]
- **Platforms:** Any modern browser (Chrome, Firefox, Safari, Edge). Offline-capable (no network resources). [Confirmed]
- **Save system:** localStorage (best streak, best score, chip balance, mute preference, daily bonus timestamp). [Estimated]
- **Accounts/auth:** None. [Confirmed — single-player, no backend]
- **Cross-device sync:** None in MVP. [Estimated]
- **Multiplayer/netcode:** N/A — single-player only. [Confirmed]
- **Anti-cheat:** N/A — no competitive multiplayer; client-only. [Confirmed]
- **Backend services:** None. [Confirmed]
- **Analytics:** None in MVP. [Estimated]
- **Third-party SDKs:** None. [Confirmed]
- **App size:** < 50 KB (single HTML file). [Estimated]
- **Performance:** 60 fps CSS animations; no canvas rendering required. Runs on low-end mobile. [Estimated]

---

## 15. Pacing & Difficulty

### Early Game (Streaks 1–4)
- Cards feel manageable; multiplier ramps gently.
- Near-50/50 cards (7, 8) appear and teach probability intuition.
- Tutorial completes; player understands tie rule.
- Churn point: player expects winning fast; if they hit a wrong guess on streak 2, they may quit. Mitigation: Lives mode offers forgiveness.

### Mid Game (Streaks 5–9)
- Multiplier becomes meaningful (5×–20×); cash-out tension peaks.
- Player starts developing intuition: "I have a 2 — always guess Higher."
- Streak 5 milestone fanfare provides positive reinforcement.
- Churn point: streak resets feel punishing. Mitigation: best streak display provides motivation to retry.

### Late Game / Expert (Streaks 10+)
- 50× cap means score gain plateaus; tension shifts entirely to cash-out decision.
- Deck reshuffle becomes relevant; probability tracking matters.
- Churn point: deck knowledge advantage disappears on reshuffle. This is intentional variance.

### Difficulty Curve
- No manual difficulty setting; difficulty is inherent in card probability.
- A dealt 7 or 8 is the hardest call (near 50/50). A 2 or Ace is trivially easy.
- Average expected correct-guess probability across all cards: ~52–55% [Estimated — accounting for tie pushes].

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---|---|---|
| 52-card deck + Fisher-Yates shuffle | ✓ | ✓ |
| Higher / Lower buttons with lock during animation | ✓ | ✓ |
| Card flip CSS animation | ✓ | ✓ |
| Streak counter + multiplier ladder | ✓ | ✓ |
| Score system | ✓ | ✓ |
| Tie = Push rule (displayed) | ✓ | ✓ |
| Game over + round summary overlay | ✓ | ✓ |
| Best streak / best score persistence | ✓ | ✓ |
| Responsive / mobile-first | ✓ | ✓ |
| WebAudio SFX + mute toggle | ✓ | ✓ |
| Classic mode | ✓ | ✓ |
| Lives mode (3 lives) | ✓ | ✓ |
| Chip betting + cash-out mode | ✓ | ✓ |
| Deck reshuffle when < 10 cards | ✓ | ✓ |
| Daily chip login bonus | ✓ | ✓ |
| Tutorial / onboarding overlay | ✓ | ✓ |
| Keyboard controls (↑ ↓ Space) | ✓ | ✓ |
| Probability hint toggle (show P% on card) | — | ✓ |
| Multiple card back themes | — | ✓ |
| Achievements system | — | ✓ |
| Daily challenge (shared deck seed) | — | ✓ |
| Leaderboard (online) | — | ✓ |
| Rewarded ad for chip refill | — | ✓ |

### Phased Roadmap

**Phase 1 — Core Engine (1–2 days)**
- Deck model: 52-card array, suits/ranks, Fisher-Yates shuffle.
- State machine: IDLE → BETTING → WAITING_GUESS → REVEALING → RESULT → ROUND_END.
- Higher/Lower logic + tie detection.
- DOM card render (CSS + Unicode).

**Phase 2 — Visual Layer (1 day)**
- Card flip CSS animation.
- Correct/wrong/tie visual feedback (glow, shake, neutral).
- HUD: streak, multiplier badge, score, best, lives/chips.
- Responsive layout (CSS Grid/Flexbox).

**Phase 3 — Game Modes + Economy (1 day)**
- Classic mode complete.
- Lives mode (3 lives, life-loss animation).
- Bet & cash-out mode (chip input, payout calculation, cash-out button).
- Deck reshuffle logic + toast notification.

**Phase 4 — Polish + Persistence (1 day)**
- WebAudio SFX (all 6 sounds, mute toggle).
- Streak milestone celebrations (confetti CSS).
- localStorage: best streak, best score, chips, mute, daily bonus.
- Tutorial overlay (first-time only).
- Game over / round summary screens.
- Keyboard controls.

**Phase 5 — QA + Final Polish (0.5 days)**
- Cross-browser test (Chrome, Firefox, Safari, Edge).
- Mobile touch test (iOS Safari, Android Chrome).
- Console error check.
- Accessibility pass (color contrast, tap target size).
- File size check (< 50 KB).

### Recommended Tech Stack
- Vanilla HTML5 + CSS3 + ES6 (no framework, no build step).
- CSS Custom Properties for theming.
- Web Audio API for generated SFX.
- localStorage API for persistence.
- No canvas — pure DOM + CSS.

### Required Asset List
- No external assets required. All visuals are CSS + Unicode. All audio is WebAudio-generated. [Confirmed]

### Hardest Parts / Risks
1. **Card flip animation timing:** Must sync CSS transition midpoint (90°) with card face/back DOM swap. Use `transitionend` or a timed 300 ms timeout at half-duration.
2. **Button lock during animation:** Race condition if player taps twice fast. Use a `isRevealing` flag; disable buttons in CSS with `pointer-events: none`.
3. **Deck probability fairness:** With a real 52-card deck, card counting is possible. Reshuffle when < 10 cards prevents extreme late-deck bias.
4. **Chip economy balance:** Starting chips, min bet, and daily bonus must be tuned so casual players aren't perpetually broke — mercy floor (reset to 200 if < 10) prevents frustration.
5. **Mobile tap target sizing:** Higher/Lower buttons must be ≥ 56px tall with clear visual affordance on small screens.

---

## 17. Open Questions

1. **Tie rule preference:** The Push (streak maintained, no bonus) rule chosen here is player-friendly. Alternatively, tie = house win (streak breaks) is more tension-filled. Could be offered as a toggle. [Needs playtesting to determine preferred feel.]
2. **Multiplier cap:** 50× cap at streak 10+ is estimated. A higher cap (100×) would be more exciting but may break chip economy — needs balance testing.
3. **Lives mode re-start:** Should lives refill between rounds in the same "session," or only on full game reset? [Design choice — recommend full reset for tension.]
4. **Audio volume balance:** WebAudio-generated tones may differ in loudness across browsers — needs cross-browser audio level testing.
