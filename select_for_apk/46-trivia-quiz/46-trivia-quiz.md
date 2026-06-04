# Trivia Quiz — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not any specific trivia game's name, logo, or copyrighted art/audio/text.

## 1. Snapshot

Trivia Quiz is a browser-based multiple-choice trivia game where players select a category and difficulty, then race to answer timed questions while building streaks for score bonuses. The core appeal is the combination of knowledge testing, time pressure, and streaks rewarding quick confident answers, with a satisfying end-screen rank and category-specific high score tracking. [Estimated — genre archetype]

**Quick facts:** Original design (genre archetype); Web/Mobile browser; Vanilla HTML/JS; No age-rating requirement; Ad-free, no IAP; Offline-capable; All ages (General rating, no COPPA flags for trivia-only).

---

## 2. Core Loops

- **30-second loop:** Read question → scan four options → click answer before timer expires → see correct/wrong highlight + score delta → next question auto-advances.
- **Session loop:** Choose category + difficulty → answer 10 questions → see end-screen (score, %, rank, streak stats) → replay or switch category.
- **Meta loop:** Beat your high score per category; unlock higher difficulties; chase "Expert" rank badge; maintain localStorage high scores across sessions.

---

## 3. Mechanics, Controls & Game States

### Game Modes
| Mode | Description |
|------|-------------|
| Quick Play | Pick category + difficulty, answer 10 questions, see results |
| (Optional) Sudden Death | One wrong = game over; pursue longest streak |

[Estimated — Quick Play is MVP; Sudden Death is stretch goal]

### Core Mechanics
- **Category select:** 6 categories — General Knowledge, Science, History, Geography, Sports, Entertainment.
- **Difficulty select:** Easy / Medium / Hard — affects question pool and time limit.
- **Question card:** Shows question text + 4 option buttons (A/B/C/D); options shuffled each round to prevent memorization of position.
- **Countdown timer:** Per-question. Easy=20s, Medium=15s, Hard=10s. Visual progress bar drains left-to-right.
- **Answer lock:** Clicking any option immediately locks input; correct answer highlights green, wrong answer highlights red + shows correct answer in green.
- **Timeout:** If timer reaches 0, auto-marks wrong; correct answer revealed.
- **Scoring formula:**
  - Base: Easy=100pts, Medium=200pts, Hard=300pts per correct answer.
  - Time bonus: `floor((timeRemaining / totalTime) × baseScore × 0.5)` added on top.
  - Streak multiplier: ×1.0 at 0–2 streak, ×1.25 at 3–4, ×1.5 at 5–6, ×2.0 at 7+.
  - Wrong/timeout = 0 pts + streak resets to 0.
- **50/50 Lifeline:** Once per game, removes 2 wrong answers, leaving 1 correct + 1 wrong. Reduces time bonus by 50% for that question.
- **Progress indicator:** "Q 3 / 10" shown in HUD.
- **No negative scoring** — wrong answers never subtract.

### Controls
- Mouse click (desktop) and touch tap (mobile) on option buttons.
- Portrait-first layout; responsive to any viewport.

### Win / Lose / Fail States
- No lose state mid-round; game always completes 10 questions.
- "Fail" = score < 30%; "Pass" = 30–59%; "Good" = 60–79%; "Great" = 80–89%; "Expert" = 90–100%.
- End screen always shown; player chooses to replay or pick new category.

### AI / Difficulty
- No AI opponent. Difficulty is purely question pool + time limit.
- Easy questions: common knowledge, unambiguous answers.
- Hard questions: specialist knowledge, historical dates, scientific specifics.

### Feedback Systems
- Correct: green flash + upward score pop-up text + ascending tone (WebAudio).
- Wrong: red flash + shake animation + descending tone.
- Streak: visual streak counter increments with particle burst at milestone levels (3, 5, 7+).
- Timer low (<5s remaining): bar turns red + urgent ticking sound.
- End screen: rank badge animates in with celebratory or neutral tone.

---

## 4. Progression

### Per-Session Progression
- Score builds question by question with streak/speed bonuses.
- Streak counter visible in HUD; milestones (3×, 5×, 7+) trigger visual reward.

### Cross-Session Progression
- **High score per category stored in localStorage** (separate record per category + difficulty combination).
- "New Record!" banner shown on end screen if current score exceeds stored high score.
- No XP, no unlock trees — progression is purely score-chasing.

### Difficulty Gating
- All difficulties available from the start. [Estimated — no lock needed for a trivia game]
- Players self-select; higher difficulty = harder questions + stricter timer.

---

## 5. Economy & RNG *(tables)*

### Currencies
| Currency | Type | Earned From | Spent On |
|----------|------|-------------|----------|
| Score Points | Session only | Correct answers + time/streak bonuses | Determines rank; compared to high score |

No soft/hard currency. No IAP. No economy beyond scoring. [Confirmed — design decision]

### Scoring Formula (Estimated)
```
questionScore = baseScore × streakMultiplier + timeBonus
timeBonus = floor((timeRemaining / totalTime) × baseScore × 0.5)
streakMultiplier: streak 0–2 → 1.0, 3–4 → 1.25, 5–6 → 1.5, 7+ → 2.0
baseScore: Easy=100, Medium=200, Hard=300
```

### Max Possible Score (Estimated)
| Difficulty | Max per question | 10 questions max |
|-----------|-----------------|-----------------|
| Easy | 150 (100 base + 50 time + ×1.0) | 1500 → 3000 with ×2.0 streak |
| Medium | 300 (200 + 100) → 600 with streak | 3000 → 6000 |
| Hard | 450 (300 + 150) → 900 with streak | 4500 → 9000 |

### RNG
- Question selection: randomly sample N questions from pool without replacement per session.
- Option shuffle: Fisher-Yates shuffle of 4 options each time a question is rendered; correct index tracked separately from display order.
- No gacha, no loot tables.

---

## 6. Content Inventory *(counts + lists)*

### Question Bank (Embedded in HTML)
| Category | Easy | Medium | Hard | Total |
|----------|------|--------|------|-------|
| General Knowledge | 10 | 8 | 7 | 25 |
| Science | 10 | 8 | 7 | 25 |
| History | 10 | 8 | 7 | 25 |
| Geography | 10 | 8 | 7 | 25 |
| Sports | 10 | 8 | 7 | 25 |
| Entertainment | 10 | 8 | 7 | 25 |
| **Total** | **60** | **48** | **42** | **150** |

Each question includes: text, 4 options, correct answer index, short explanation shown after answer.

### Game Modes: 1 (Quick Play, 10 questions per session)
### Categories: 6
### Difficulties: 3
### Lifelines: 1 (50/50)
### Ranks: 5 (Fail → Pass → Good → Great → Expert)

---

## 7. Theme, Narrative & Tone

- **Setting:** Clean, modern quiz-show aesthetic. No fictional world or characters.
- **Premise:** Player is a contestant in a solo knowledge challenge. No story, no narrative.
- **Tone:** Bright, competitive, encouraging. Celebrates correct answers; gentle on wrong ones.
- **Color palette:** Deep indigo/navy background (#1a1a3e), card white (#fff), accent teal (#00c4cc), correct green (#22c55e), wrong red (#ef4444), streak orange (#f97316).
- **Typography:** Sans-serif (system stack); large readable question text; chunky option buttons.
- **IP:** No licensed IP. All questions are original factual trivia. [Confirmed — design decision]

---

## 8. Meta & Social Systems

- **High scores:** Per-category + per-difficulty stored locally. No cloud sync.
- **No daily missions, achievements, events, or battle pass** — out of scope for MVP. [Estimated]
- **No multiplayer, guilds, or leaderboards** — single-player only.
- **No social sharing** in MVP; stretch goal: "Share your score" text copy.
- **No live-ops cadence required** — question bank is embedded; no server dependency.

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|--------|---------|
| Start / Home | Title, category grid, difficulty selector, high score display, How-to-Play button |
| How-to-Play Modal | Rules overlay: timer, scoring, streak, lifeline explained |
| Game Screen | Active question, HUD, option buttons, timer bar, lifeline button |
| Answer Review | Brief (2s) overlay: shows correct/wrong, explanation text, score delta |
| End Screen | Final score, %, rank badge, question review list, New Record banner, Replay / Change Category buttons |
| Settings Modal | Sound on/off toggle, high score reset per category |

### HUD Elements (during gameplay)
- Category name + difficulty badge (top-left)
- Score (top-right, animates on change)
- Streak counter + flame icon (center-top or below score)
- Question progress "Q X / 10" (below category)
- Timer progress bar (below question text, full width)
- 50/50 lifeline button (bottom-left, grayed after use)
- Mute toggle (top-right corner)

### Navigation Flow
```
Start Screen → [select category + difficulty] → Game Screen
Game Screen → [answer/timeout] → Answer Review → [next question] → Game Screen
Game Screen → [Q10 done] → End Screen
End Screen → [Replay] → Game Screen (same settings)
End Screen → [Change Category] → Start Screen
Start Screen → [How to Play] → How-to-Play Modal → Start Screen
Start Screen / anywhere → [Settings] → Settings Modal
```

### Onboarding / Tutorial (First-Time User Flow)
1. Game loads → Start Screen shows with category grid visible.
2. "How to Play" button prominently displayed; first-time auto-shows brief tooltip: "Pick a category to start!"
3. Player taps a category → difficulty row expands below it.
4. Player taps a difficulty → "Start Quiz" button appears.
5. First question loads → HUD elements have brief label tooltips (disappear after 3s).
6. No forced tutorial; game is self-explanatory. Rules accessible via modal at any time.

---

## 10. Art, Audio, Camera & Feel

- **Dimension:** 2D flat web UI.
- **Camera/perspective:** None — static card layout.
- **Orientation:** Portrait-first (mobile); centered column on desktop (max-width 600px).
- **Art style:** Clean flat design, soft card shadows, rounded corners, subtle gradients on buttons.
- **Palette:** Navy background, white cards, teal accents, vivid correct/wrong states, orange streak highlights.
- **Animation:**
  - Option buttons: scale-up on hover, pulse on lock, shake on wrong.
  - Score: count-up animation on change.
  - Timer bar: smooth CSS transition drain.
  - Streak counter: bounce at milestones.
  - End screen rank badge: scale-in from 0 with slight overshoot (CSS spring).
- **VFX:** CSS-only — no canvas particles needed; box-shadow glow on correct, red border flash on wrong, streak fire emoji cascade at 5+ streak.
- **SFX (WebAudio generated, no files):**
  - Correct: ascending 2-tone chime (440 → 660 Hz, 0.15s each).
  - Wrong: descending buzzer (300 → 200 Hz, 0.3s).
  - Tick: light click at 1Hz when timer <5s (900 Hz, 0.05s).
  - Streak milestone: short fanfare arpeggio.
  - Game complete: 3-note resolution chord.
  - All routed through mute toggle (AudioContext suspended/resumed).
- **Music:** None — avoids licensing issues; SFX provide sufficient audio feedback.

---

## 11. Monetization

- **None.** No ads, no IAP, no subscriptions. [Confirmed — design decision for this offline web game]
- **Consent/ATT/CMP:** Not required — no tracking, no ad SDKs, no data collection.

---

## 12. Retention Hooks

- **High score persistence:** localStorage stores best score per category+difficulty; visible on Start Screen → motivation to replay and beat it.
- **Rank system:** "Expert" rank only achievable with near-perfect accuracy + speed → drives replay.
- **Streak mechanic:** Building and protecting a streak creates tension; losing it motivates retry.
- **No daily rewards, idle earnings, push notifications, or energy system** — game is stateless between sessions beyond high scores. [Confirmed — design decision]
- **Question variety:** 150 questions, 10 per session = ~25 unique sessions per category before repeats; randomization feels fresh.

---

## 13. Localization & Accessibility

- **Language:** English only in MVP. [Estimated]
- **RTL:** Not supported in MVP.
- **Text scaling:** Viewport-relative font sizes; user OS font-size respected via `rem`.
- **Colorblind support:** Correct/wrong states use both color AND icon (✓ / ✗) so red-green colorblind players are not disadvantaged.
- **Touch target size:** All interactive elements minimum 44×44px per WCAG 2.1 guidelines.
- **Keyboard navigation:** Option buttons focusable; Enter/Space to select; Escape to mute toggle.
- **Age/content rating:** General audience (IARC 3+). No violence, no adult content, no user data collected → no COPPA/GDPR-K concerns.
- **Difficulty assist:** Easy mode is accessible for younger/casual players; no forced difficulty.

---

## 14. Technical Structure

- **Engine/framework:** Vanilla HTML5 + CSS3 + ES6 JavaScript. No build step, no dependencies.
- **Platform:** Any modern browser (Chrome, Firefox, Safari, Edge); mobile and desktop.
- **Online/offline:** Fully offline. No network requests. Double-click to play.
- **Save system:** localStorage only. Keys: `tq_highscore_{category}_{difficulty}`, `tq_settings_mute`.
- **Accounts/auth:** None. Guest/anonymous only.
- **Cross-device sync:** Not supported (localStorage is device-local).
- **Multiplayer/netcode:** None.
- **Anti-cheat/server authority:** N/A — single-player, client-side only, no competitive stakes.
- **Analytics:** None.
- **Third-party SDKs:** None.
- **App size:** ~50–80 KB total (HTML + embedded questions).
- **Performance:** No canvas, no heavy libs; 60fps CSS animations; runs on low-end devices.

---

## 15. Pacing & Difficulty

### Early Game (Questions 1–3)
- Straightforward, familiar questions; player gets a feel for the timer rhythm.
- Streak starts at 0; building first combo feels achievable.
- **Aha moment:** First streak hit at Q3 → "Oh, I get bonuses for speed!"

### Mid Game (Questions 4–7)
- Questions get trickier (if Medium/Hard). Timer pressure builds.
- Player may lose first streak → tension and focus increase.
- 50/50 lifeline use decision: save for a hard one or use early?

### Late Game (Questions 8–10)
- High stakes: player knows final score is close. Timer anxiety peaks.
- Streak protected if player is doing well → multiplier at maximum.
- Timeout risk highest on hardest questions.

### Churn Points [Estimated]
- If questions feel "too obscure" for chosen difficulty → frustration → abandon.
- Timer too short on Hard → feels unfair → mitigation: always show correct answer + explanation.
- No progression unlock → experienced players may not return after seeing all questions.

### Difficulty Curve (within a session)
- Question order within a session is random, not scaled by difficulty.
- [Estimated stretch: sort questions easy→hard within session for better pacing arc]

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist
| Feature | MVP | Full |
|---------|-----|------|
| Category + difficulty select | ✓ | ✓ |
| 10-question session | ✓ | ✓ |
| Per-question timer | ✓ | ✓ |
| Answer lock + reveal | ✓ | ✓ |
| Score + streak + time bonus | ✓ | ✓ |
| End screen with rank | ✓ | ✓ |
| localStorage high score | ✓ | ✓ |
| 150 embedded questions | ✓ | ✓ |
| WebAudio SFX | ✓ | ✓ |
| Mute toggle | ✓ | ✓ |
| 50/50 lifeline | ✓ | ✓ |
| Answer explanations | ✓ | ✓ |
| Mobile responsive | ✓ | ✓ |
| How-to-Play modal | ✓ | ✓ |
| Keyboard navigation | — | ✓ |
| Sudden Death mode | — | ✓ |
| Share score text | — | ✓ |
| Daily challenge | — | ✓ |

### Phased Roadmap
**Phase 1 — Core (Week 1)**
- HTML skeleton: start screen, game screen, end screen.
- Question data structure + 30 seed questions (5 per category, Easy only).
- Category/difficulty selector UI.
- Basic question render + 4-option buttons.

**Phase 2 — Mechanics (Week 1–2)**
- Per-question countdown timer (CSS bar + JS interval).
- Answer lock logic + correct/wrong highlight.
- Score calculation: base + time bonus.
- Progress indicator (Q x/10).

**Phase 3 — Juice & Polish (Week 2)**
- Streak counter + multiplier.
- CSS animations (shake, pulse, bounce).
- WebAudio SFX (correct, wrong, tick, complete).
- Mute toggle.

**Phase 4 — Content & Persistence (Week 2–3)**
- Full 150-question bank (25 per category × 3 difficulties).
- localStorage high score per category+difficulty.
- "New Record!" banner.
- 50/50 lifeline.

**Phase 5 — UX Finalization (Week 3)**
- Answer explanation display.
- End-screen question review.
- How-to-Play modal.
- Mobile touch optimization; 44px targets.
- Cross-browser testing.

### Recommended Tech Stack
- HTML5 / CSS3 / Vanilla ES6 JS — no framework needed.
- CSS custom properties for theming.
- Web Audio API for SFX.
- localStorage for persistence.

### Required Asset List
- No external images required (CSS-only UI).
- No audio files (WebAudio generated).
- 150 trivia questions with 4 options + correct index + explanation (embedded JS array).
- Category icons: Unicode emoji sufficient (🌍 Geography, ⚗ Science, etc.).

### Hardest Parts / Risks
1. **Question accuracy:** Every question must be verifiably correct. Wrong questions destroy trust. Mitigation: fact-check each question against multiple sources; include explanations that double as verification.
2. **Option shuffle + correct tracking:** Fisher-Yates shuffle must track the correct answer's new position, not just its original index. A bug here causes false "wrong" answers. Mitigation: store `correctText` not `correctIndex`; compare against selected option's text.
3. **Timer race condition:** If player answers at exactly T=0, double-event (timeout + click) may fire. Mitigation: `answered` boolean flag set atomically on first event; ignore subsequent events.
4. **Mobile touch responsiveness:** Timer must feel tight on mobile; avoid lag on touch events. Mitigation: `touchstart` not `touchend` for option selection.
5. **Question pool exhaustion:** 25 questions per category means repeats after ~3 sessions. Mitigation: 150 total questions, random sampling makes this unlikely in casual play.

---

## 17. Open Questions

1. **Session length:** 10 questions chosen for MVP. Should playtesting show fatigue, a 5-question "quick" mode could be added. [Needs playtesting to confirm]
2. **Hard question difficulty calibration:** The line between "Hard" and "Obscure" is subjective; some Hard questions may need tuning after player feedback.
3. **Timer values (Easy=20s, Medium=15s, Hard=10s):** Estimated based on genre norms; may need adjustment depending on average question length.
4. **Whether a Sudden Death mode adds replay value:** Depends on player skill distribution — worth A/B testing if metrics are available.
