# Hangman — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not the original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Hangman is one of the oldest and most universally recognized word-guessing games. A hidden word is represented as a row of blank dashes; the player guesses letters one at a time. Each correct guess fills in matching blanks; each wrong guess adds one piece to a stylized "hangman" figure. The player wins by completing the word before the figure is fully drawn (6–7 wrong guesses allowed). The digital version adds categories, optional hints, score and streak tracking, and sound feedback for a polished casual word-puzzle experience.

**Quick facts:**
- Genre: Word puzzle / casual brain-training
- Origin: Traditional pencil-and-paper game (19th century) [Confirmed]
- Digital platforms: Web, Android, iOS, PC — ubiquitous
- Session length: 1–3 minutes per word; typical sitting 5–15 minutes [Estimated]
- Target audience: Ages 8+ (family-friendly, educational crossover)
- Content rating: IARC 3+ / ESRB E [Estimated]
- Monetization model (this clone): Free to play, no ads, score-based engagement
- Comparable titles: Word Guess, Wordle, Word Connect, Wheel of Fortune (word-reveal mechanic)

---

## 2. Core Loops

- **30-second loop:** Player views blank word + category label → guesses a letter via on-screen button or keyboard → correct: blanks fill in with that letter (reveal animation) → wrong: next hangman stage draws, wrong-letter list updates → repeat until win or lose.
- **Session loop:** Choose category → play word → win/lose overlay shows result + score delta → tap "Next Word" → new word from same or re-selected category → accumulate streak and score → optionally use hint (costs score) → try to beat best streak.
- **Meta loop:** Score and streak persisted in localStorage → player returns to beat their best streak → category collection (completing all categories) → optional daily challenge seed for a consistent word across sessions.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics
1. **Word selection:** Random word drawn from the selected category's pool; word is normalized to uppercase.
2. **Blank display:** One underscore dash per letter, spaces preserved for multi-word phrases (optional).
3. **Letter guessing:** Player picks any A–Z letter not yet guessed. Correct → all instances of that letter revealed. Wrong → hangman stage incremented, letter added to "wrong" list.
4. **Hint system:** Reveals one unrevealed letter (player's choice is automatic — picks a random unrevealed letter). Costs 3 points. Maximum 2 hints per word [Estimated].
5. **Win detection:** All non-space letters revealed → win state.
6. **Lose detection:** Wrong-guess count reaches max (6) → lose state, full word revealed.
7. **Streak:** Consecutive wins without a loss. Streak resets on any loss.
8. **Score:** Points awarded per win based on remaining lives and speed [see Economy section].
9. **Category selector:** Shown before each round or on-demand; picking a new category starts a fresh word but does not reset streak mid-game (resets only on loss).

### Game Modes
| Mode | Description |
|---|---|
| Classic | Standard hangman, 6 wrong guesses, any category [Confirmed] |
| Category Challenge | All words come from one chosen category, tracks category high score [Estimated] |
| Daily Word | Seeded word of the day, same for all players, once per day [Estimated] |

### Controls
- **On-screen A–Z keyboard:** 26 letter buttons in 3 rows (QWERTY layout); each button disabled after use (correct = green, wrong = red/gray).
- **Physical keyboard:** `keydown` listener for A–Z; Enter to advance on win/lose overlay.
- **Hint button:** Taps/clicks reveal one letter at score cost.
- **Orientation:** Portrait-primary on mobile; landscape works with flex reflow.

### Win / Lose / Fail States
| State | Trigger | Outcome |
|---|---|---|
| Win | All letters revealed | +score, +streak, show win overlay, confetti burst |
| Lose | 6 wrong guesses | streak resets, reveal word highlighted in red, show lose overlay |
| Hint used | Hint button tapped | One letter auto-revealed, score penalty applied |

### Difficulty Modes
- **Easy:** 8 wrong guesses allowed, short common words, hint free [Estimated]
- **Normal (default):** 6 wrong guesses, mixed length words, hints cost points
- **Hard:** 6 wrong guesses, longer/rarer words, no hints [Estimated]

### Feedback Systems
- **Visual:** Correct letter → green flash on blank + button; wrong → red flash on hangman stage + button grays out; win → confetti particles; lose → hangman shakes.
- **Audio (WebAudio):** Soft chime for correct; low thud for wrong; fanfare for win; descending tones for lose; click for button press; mute toggle.
- **Haptic:** Not implemented (web limitation).

---

## 4. Progression

### Score System
- Base score per word won: 10 points [Estimated]
- Bonus per remaining life: +2 points each [Estimated]
- Streak multiplier: streak × 0.5 added as multiplier (streak 1 = ×1.0, streak 5 = ×3.5) [Estimated]
- Hint penalty: −3 points per hint used [Estimated]

### Streak
- Increments on each win; resets on loss.
- Best streak persisted to localStorage.

### Unlocks (optional future)
- No hard gates in MVP; future: unlock "Hard mode" after streak ≥ 10, unlock new category after playing 20 words in adjacent category [Estimated].

### Difficulty Scaling
- Word length and rarity can increase as streak grows (streak ≥ 5 pulls from medium-difficulty sub-pool, streak ≥ 10 from hard sub-pool) [Estimated].

---

## 5. Economy & RNG *(tables)*

### Currencies
| Currency | Type | Earned From | Spent On |
|---|---|---|---|
| Score Points | Soft (session) | Winning words, remaining lives bonus, streak multiplier | Hints (−3 pts each) |
| Best Score | Persistent | Score at end of best session | Display only |
| Streak | Counter | Consecutive wins | Resets on loss |

### Cost Scaling
- No upgrade/purchase economy; score is a performance metric, not a spendable resource in the traditional sense.
- Hint cost: flat 3 points per use regardless of streak or word length [Estimated].
- Formula for word score: `score = (10 + remaining_lives × 2) × (1 + streak × 0.5)` [Estimated]

### Score Examples (Estimated)
| Lives Left | Streak | Score |
|---|---|---|
| 6 (perfect) | 1 | (10+12) × 1.5 = 33 |
| 4 | 1 | (10+8) × 1.5 = 27 |
| 6 (perfect) | 5 | (10+12) × 3.5 = 77 |
| 0 (loss) | any | 0, streak resets |

### RNG
- Word selected uniformly at random from the active category pool (no weighting).
- Same word will not repeat until the pool is exhausted (Fisher-Yates shuffle per session) [Estimated].
- Daily challenge: deterministic seed from `YYYY-MM-DD` string hashed to pool index.
- No gacha, no drop rates, no loot boxes.

---

## 6. Content Inventory *(counts + lists)*

### Categories & Word Counts (Embedded in HTML)
| Category | Word Count | Example Words |
|---|---|---|
| Animals | 40 | ELEPHANT, PENGUIN, CROCODILE, BUTTERFLY |
| Countries | 40 | BRAZIL, ICELAND, THAILAND, ARGENTINA |
| Food & Drink | 40 | AVOCADO, SPAGHETTI, CROISSANT, BLUEBERRY |
| Sports | 35 | BADMINTON, WRESTLING, VOLLEYBALL, TRIATHLON |
| Science | 35 | TELESCOPE, MOLECULE, EVOLUTION, PHOTOSYNTHESIS |
| Movies | 35 | INCEPTION, GLADIATOR, INTERSTELLAR, CASABLANCA |
| Geography | 35 | HIMALAYA, AMAZON, SAHARA, ANTARCTICA |
| Technology | 30 | ALGORITHM, BLUETOOTH, ENCRYPTION, PROCESSOR |
| Music | 30 | SAXOPHONE, SYMPHONY, BEETHOVEN, ACOUSTIC |
| Mythology | 30 | MEDUSA, POSEIDON, MINOTAUR, PERSEPHONE |
| **Total** | **~350** | — |

### Hint Text
Each word carries one optional hint string (e.g., "ELEPHANT → 'Largest land animal'") embedded alongside the word in the data array [Estimated].

### Drawing Stages (7 parts)
1. Gallows structure (post + beam + rope) — always shown as background
2. Head (circle)
3. Body (vertical line)
4. Left arm
5. Right arm
6. Left leg
7. Right leg

Max wrong guesses: 6 (stages 2–7). Stage 1 (gallows) is always visible.

---

## 7. Theme, Narrative & Tone

- **Setting:** Minimalist word-puzzle environment; no narrative world or story.
- **Visual theme:** Clean, modern card-based UI. Dark navy/charcoal background, cream/white card for game area, accent color per category (teal, coral, amber, etc.).
- **Tone:** Friendly, educational, slightly whimsical. Not grim despite the "hangman" imagery — the figure is stylized/cartoonish, not realistic.
- **Character:** The hangman figure is a simple stick figure — kept light and abstract [Confirmed as genre convention].
- **Writing style:** Category names and hints use plain, accessible English. No story beats, no dialogue.
- **IP:** None. Pure mechanic clone; original branding applied.

---

## 8. Meta & Social Systems

- **Streak tracking:** Best streak saved locally and displayed in HUD.
- **Best score:** Persisted to localStorage.
- **Daily Word mode:** Same word for all players on a given date using seeded RNG [Estimated].
- **Achievements (optional future):** "First Win", "Streak of 5", "Perfect Word (no wrong guesses)", "All Categories Played" [Estimated].
- **No multiplayer:** Single-player only in this implementation.
- **No leaderboards:** Local-only score tracking in MVP.
- **No battle pass / seasons / live ops:** Static content, no backend required.
- **Share result:** Optional "Share score" button that copies a text result to clipboard (emoji art of gallows state) [Estimated].

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|---|---|
| Splash / Title | Game name, "Play" button, brief tagline |
| Category Selector | Grid of category cards with icon + name; tap to choose |
| Game Screen | Main gameplay: hangman canvas, word blanks, letter keyboard, HUD |
| Win Overlay | "Word guessed!" message, score earned, streak, "Next Word" button |
| Lose Overlay | "Out of guesses!" message, word revealed, "Try Again" button |
| How to Play Modal | Rules summary (4–5 bullet points), dismiss button |
| Settings Modal | Difficulty selector, mute toggle, reset stats |
| Stats Panel (optional) | Wins, losses, best streak, words played |

### Settings / Options Menu Contents
- Difficulty: Easy / Normal / Hard
- Sound: On / Off (mute toggle)
- Reset Statistics (with confirmation prompt)
- How to Play shortcut link

### Gameplay HUD Elements
- Category label (top-left)
- Score (top-right)
- Streak counter (top-right, below score)
- Best streak (top-right, below streak)
- Hangman canvas (center-left or top-center)
- Word blanks (center)
- Wrong letters display ("Wrong: B, F, K…")
- Wrong count / max ("3 / 6")
- Hint button (bottom, shows cost)
- Letter keyboard (bottom section)

### Navigation Flow
```
Title → Category Selector → Game Screen ↔ Win/Lose Overlay → (Next Word → Game Screen)
                                        ↕
                               How to Play / Settings (modal overlays)
```

### Onboarding / Tutorial (First Visit)
1. Overlay appears: "Welcome to Hangman!" with brief rules.
2. Category selector highlighted — prompt: "Choose a category to begin."
3. First word loads; tooltip points to word blanks: "Guess the hidden word!"
4. Tooltip points to keyboard: "Tap letters to guess."
5. If first guess is wrong, tooltip appears on hangman: "Wrong guesses build the figure — 6 and you lose!"
6. Tutorial dismissed; normal play continues.
7. Flag stored in localStorage so tutorial never repeats.

---

## 10. Art, Audio, Camera & Feel

### Dimension & Camera
- 2D, no camera — static layout [Confirmed]
- Portrait-primary; landscape reflows to side-by-side (canvas left, keyboard right)
- Viewport: `<meta name="viewport" content="width=device-width, initial-scale=1">`

### Art Style
- Flat / minimal design language; rounded corners, subtle shadows
- Color palette: Dark background (#1a1a2e navy), card background (#16213e), accent (#e94560 coral red for hangman), letter buttons (#0f3460 navy with white text), correct letter highlight (#27ae60 green), wrong letter (#e74c3c red)
- Typography: System sans-serif (no web fonts required for offline play); large, bold word blanks
- Hangman figure: SVG or Canvas line art — thin stroke, rounded line-caps, minimalist stick figure

### Animation & VFX
- Letter reveal: scale-up + fade-in on blank
- Wrong guess: hangman stage draw with short stroke animation (CSS stroke-dasharray transition)
- Win: confetti particle burst (CSS/JS keyframe), screen flash green
- Lose: shake animation on hangman figure, screen flash red, word blanks turn red then reveal
- Button press: subtle scale-down (0.95) on tap
- Streak increment: badge bounce

### Audio (WebAudio API — no files)
| Event | Sound Description |
|---|---|
| Correct letter | Bright ascending short tone (sine wave, ~440Hz) |
| Wrong letter | Low short thud (sawtooth, ~120Hz) |
| Win | Ascending fanfare arpeggio (C-E-G-C) |
| Lose | Descending sad tones (C-A-F) |
| Hint used | Soft whoosh / reveal chime |
| Button press | Quiet click (noise burst, <50ms) |

- Mute button: speaker icon in header; persisted to localStorage.

---

## 11. Monetization

- **This clone: No monetization.** Pure free-to-play, no ads, no IAP.
- No ad placements, no IAP product table, no loot boxes, no subscriptions.
- No ATT prompt, no GDPR/CMP consent popup required (no tracking, no ads).
- No age gate required for mechanics alone (content is IARC 3+).
- If monetization were added in future: rewarded video for extra hints, IAP for "remove ads" bundle, no gacha [Estimated for reference only].

---

## 12. Retention Hooks

### Active Retention
- **Streak system:** Visual and score incentive to keep winning; streak loss is a strong retry motivator.
- **Best score / best streak:** Persistent personal best always visible; "beat your best" drive.
- **Daily Word:** One guaranteed new unique word per day, shareable result [Estimated].
- **Category variety:** 10 categories provide content diversity to prevent staleness.

### Idle / Offline
- **No idle/offline earnings:** This is an active word game; no offline progression mechanic.
- **No energy/lives system:** Play unlimited rounds at any time.
- **No push notifications:** Web-only implementation; optional future Web Push for daily word reminder.

### FOMO / Urgency
- None in MVP — deliberately casual and low-pressure.
- Daily Word mode creates mild daily check-in incentive without hard FOMO.

---

## 13. Localization & Accessibility

### Language
- English only in MVP [Estimated]; architecture supports swapping word lists per locale.
- No RTL support required for English; RTL could be added by flipping layout direction.

### Accessibility
- **Keyboard support:** Full physical keyboard play (A–Z, Enter) — screen-reader friendly.
- **Font sizing:** Base 16px+; word blanks 32–48px; letter buttons minimum 44×44px tap targets.
- **Color contrast:** WCAG AA minimum — white text on dark backgrounds, high-contrast overlays.
- **Colorblind mode (optional):** Use shape/icon differentiation alongside color for correct/wrong letters (checkmark / X icon on buttons) [Estimated].
- **Difficulty assist:** Easy mode provides more guesses and free hints.
- **No flashing:** Win animation uses gentle confetti, not strobing effects.

### Content Rating & Compliance
- IARC 3+ / ESRB Everyone [Estimated]
- No user-generated content, no chat, no personal data collected.
- COPPA compliant by design (no account creation, no data collection).
- GDPR compliant (only localStorage used; no server-side tracking).

### Regional
- No regional word list differences in MVP.
- Future: regional spelling variants (colour/color) and locale-specific category themes.

---

## 14. Technical Structure

### Engine / Stack
- Vanilla HTML5 + CSS3 + JavaScript (ES6+)
- No framework, no build step, no external dependencies
- Canvas API or inline SVG for hangman drawing
- WebAudio API for sound generation

### Platform
- Primary: Web browser (desktop + mobile)
- Offline-capable: fully self-contained single .html file
- No server required; no network requests at runtime

### Save System
- **localStorage keys:**
  - `hangman_score` — current session score
  - `hangman_best_score` — all-time best score
  - `hangman_streak` — current streak
  - `hangman_best_streak` — all-time best streak
  - `hangman_mute` — audio mute state (boolean)
  - `hangman_difficulty` — selected difficulty
  - `hangman_tutorial_done` — tutorial shown flag
  - `hangman_daily_[YYYY-MM-DD]` — daily word completion flag
- No cloud sync, no accounts, no auth

### Multiplayer / Netcode
- N/A — single-player only. No anti-cheat needed.

### Backend / Analytics
- None required. No remote config, no A/B testing, no attribution SDK.

### Performance
- Target: 60fps animations; smooth on mid-range phones (iPhone 8, Android 2018+)
- File size: < 80KB (all inline, no images) [Estimated]
- No third-party SDKs

---

## 15. Pacing & Difficulty

### Early Game (0–5 words)
- Short, common words (4–6 letters) — APPLE, TIGER, PIZZA
- Tutorial guidance on first word
- Forgiving: easy category defaults, plenty of common letters

### Mid Game (6–20 words)
- Medium-length words (6–9 letters) — ELEPHANT, VOLCANO, BASEBALL
- Streak building creates investment; player understands hint economics
- Score multiplication starts to feel meaningful at streak 3–5

### Late / Endgame (streak 10+)
- Longer, rarer words (9–13 letters) — PHOTOSYNTHESIS, PERSEPHONE, ENCRYPTION
- Hard mode available; hint budget becomes strategic
- High-pressure: one loss resets a large streak multiplier

### Key Milestone Moments
1. **First win:** Immediate positive reinforcement
2. **Streak 3:** Multiplier visibly kicks in — "aha" moment for score growth
3. **Streak 5:** Score jumps noticeably; player shifts to strategic letter-guessing
4. **Perfect word:** No wrong guesses — trophy animation, bonus points

### Churn Points [Confirmed from genre review research]
- Frustrating loss on a long streak (primary churn driver)
- Obscure words in hard category → "unfair" perception
- Running out of hints mid-difficult word

### Mitigation
- Easy mode available; hint button always visible; wrong guesses clearly counted; category can be changed freely.

---

## 16. Clone Build Plan

### MVP Feature Set
- [x] Single .html file, fully offline
- [x] 6 wrong-guess hangman with 7-stage SVG/Canvas drawing
- [x] 5+ embedded word categories with hint text
- [x] On-screen A–Z keyboard + physical keyboard
- [x] Win/lose detection and overlays
- [x] Score + streak + best streak display
- [x] Hint button (letter reveal, score cost)
- [x] localStorage persistence
- [x] WebAudio SFX + mute toggle
- [x] Responsive mobile layout
- [x] How-to modal

### Full Feature Set (post-MVP)
- [ ] Daily Word mode with seeded RNG
- [ ] Difficulty selector (Easy/Normal/Hard)
- [ ] 10+ categories with 350+ words
- [ ] Colorblind accessibility mode
- [ ] Share result (clipboard)
- [ ] Category completion tracking
- [ ] Streak achievement badges
- [ ] Animated letter reveal (stroke animation)
- [ ] Category-specific color themes

### Phased Roadmap

**Phase 1 — Core Mechanics (Day 1–2)**
- HTML skeleton, CSS layout (dark theme, responsive)
- Hangman SVG/Canvas drawing engine (7 stages)
- Word/blank display logic
- Letter guessing engine (correct fill, wrong tally)
- Win/lose detection

**Phase 2 — Content & Controls (Day 3)**
- Embed all 10 word categories with hints
- On-screen A–Z keyboard with state tracking
- Physical keyboard handler
- Category selector screen

**Phase 3 — Score, Streak & Persistence (Day 4)**
- Score formula implementation
- Streak counter + multiplier
- Best score/streak tracking
- localStorage read/write

**Phase 4 — Polish & UX (Day 5)**
- WebAudio SFX generation
- Win/lose animations (confetti, shake)
- Letter button animations (green/red)
- Hint button with cost deduction
- How-to modal + onboarding tooltip
- Mute toggle

**Phase 5 — QA & Hardening (Day 6)**
- Cross-browser test (Chrome, Firefox, Safari, Edge)
- Mobile touch test (iOS Safari, Android Chrome)
- Edge cases: repeated letters, all-same-letter word, single-letter word
- localStorage corruption handling
- Final file size check

### Recommended Tech Stack (for HTML clone)
- Vanilla JS ES6+ (classes or module pattern)
- CSS custom properties for theming
- SVG inline for hangman (scalable, no canvas needed)
- WebAudio API for SFX (no audio files)
- localStorage for persistence

### Required Asset List
- No external assets (all inline)
- Word lists: JSON-style JS array per category (~350 entries)
- SVG hangman: 7 path/element definitions
- Font: system-ui stack (no download needed)

### Hardest Parts / Risks
1. **Repeated-letter reveal correctness:** Must fill ALL instances of a guessed letter simultaneously; off-by-one in index matching is a common bug — guard carefully.
2. **Hangman drawing stages:** SVG element show/hide must map precisely to wrong-guess count; mismatch causes visual/logic desync.
3. **Keyboard dual-input guard:** Physical keyboard and on-screen button must both use the same "already guessed" check; easy to get double-fire.
4. **Mobile keyboard layout:** 26 buttons must be legible and tappable on a 375px wide screen — requires careful responsive sizing.
5. **Score overflow on long streaks:** Formula can produce very large numbers at streak 20+; cap or display in K format.

---

## 17. Open Questions

1. **Word difficulty sub-pools per streak level:** Exact thresholds for easy/medium/hard word pulls need playtesting to feel fair vs. frustrating. [Would tune post-MVP]
2. **Daily Word seed algorithm:** Whether to use a simple date-hash or a curated editorial calendar. Simple hash assumed in this build. [Low risk]
3. **Exact confetti particle count and duration:** Minor visual tuning — set to 60 particles / 2s; may need adjustment on low-end devices. [Low risk]
4. **Category icon art:** Blueprint uses text emoji icons as placeholders; final polish would use custom SVG icons. [Cosmetic only]

---

*Blueprint complete. All 17 sections filled. Economy tables included. Win/lose/fail states defined. Screen map and onboarding flow step-by-step. No monetization (ads/IAP/gacha) in this clone. No server-side components. Single-player only — no anti-cheat needed. Build plan is phased with risks flagged.*
