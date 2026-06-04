# Reflex Rush — Reaction / Reflex Test — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not any original's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

Reflex Rush is a browser-based reaction-time testing and mini-game suite. The core appeal is pure, quantified speed: the player waits for a stimulus, reacts as fast as possible, and is shown an exact millisecond reading alongside a personal rating. Three modes provide variety — the classic "Wait for Green" reaction strip, a tap-the-target spawner, and a color-match challenge. It is a casual/skill measurement game, not a progression-based title. Sessions are short (1–3 minutes), and the hook is self-improvement and sharing best times.

**Quick facts:**
- Developer: Original build (this document)
- Platform: Web (single HTML file, offline-capable)
- Genre: Casual / Skill / Reflex
- Modes: 3
- Session length: ~1–3 min per mode run
- Age/content rating: IARC 3+ (no violence, no mature content) [Estimated]
- Monetization: None (free, no ads, no IAP)
- Comparable games: Human Benchmark (humanbenchmark.com), Reaction Time Test (reactiontest.com), ReactionF1, ArealMe Reaction Test

---

## 2. Core Loops

- **30-second loop:** Player chooses a mode, waits for the go-signal (random delay 1.5–4.5 s), presses as fast as possible, sees their ms result. False starts penalize the trial.
- **Session loop:** Complete a 5-trial run in one mode → view per-trial breakdown + average + personal best → choose to retry or switch mode. One run takes ~90–120 seconds.
- **Meta loop:** Beat personal best stored in localStorage per mode; the rating label (e.g., "Superhuman / Pro / Fast / Average / Slow") drives self-competition. No cross-session progression beyond best times.

---

## 3. Mechanics, Controls & Game States

### Game States (Mode 1 — Classic Reaction Strip)
1. **IDLE** — Mode select screen shown, no timer running.
2. **READY** — Large colored panel shows red; text reads "Wait… get ready". A `setTimeout` fires after a random delay (1500–4500 ms, uniform distribution).
3. **FALSE START** — If player presses during READY state, trial is marked `false_start` (+500 ms penalty display), round resets after a brief penalty screen.
4. **GO** — Panel turns green; `performance.now()` timestamp is recorded. Player must press.
5. **RESULT** — Time delta displayed in ms. Trial logged. If fewer than 5 trials done, advance to next READY after a short pause; otherwise advance to SUMMARY.
6. **SUMMARY** — All trial times shown; average (excluding false starts from mean calculation, counted as forfeit rounds); session best; all-time best; rating label; Retry / Change Mode buttons.

### Mode 2 — Tap the Target
- Small circular targets (40–60 px radius on mobile) spawn one at a time at random positions within the safe play area.
- Player has 3 seconds to tap/click the target before it disappears and scores a miss.
- Run: 10 targets. Score = targets hit / 10. Average reaction per hit shown.
- No false-start mechanic (target must be visible to register a hit).
- Targets animate in (scale-up 100 ms) and pulse to indicate active.

### Mode 3 — Catch the Color
- A 3×3 grid of colored tiles shown. One tile is highlighted as the "target color" at top.
- On GO, all tiles shuffle to random colors. Player must tap the tile matching the target color as fast as possible.
- 8 rounds. Track: correct taps, wrong taps (penalty +200 ms added to round time), and average correct-response time.
- Introduces a cognitive-reflex dimension (identify + tap vs. pure tap).

### Controls
- **Primary:** Click or tap anywhere on the reaction area (Mode 1), or on specific targets/tiles (Modes 2 & 3).
- **Keyboard (Mode 1):** Space bar or Enter key triggers the reaction.
- **Mobile:** Touch events; `touchstart` used (not `click`) on reaction area for minimal latency.
- **Orientation:** Portrait-first on mobile; landscape also supported.
- **Anti-double-trigger:** A 300 ms lockout after first press prevents duplicate registrations.

### Win / Lose / Fail Conditions
| Outcome | Trigger | Consequence |
|---|---|---|
| Clean trial | Press after GO | Time recorded in ms |
| False start | Press before GO (Mode 1) | Round restarted, warning shown, penalty indicator |
| Miss (Mode 2) | Target expires | Miss counter +1, target removed |
| Wrong tap (Mode 3) | Wrong tile | Penalty +200 ms, round continues |
| Run complete | All trials finished | SUMMARY screen |

### Feedback
- **Visual:** State-color changes (red → green, target pulses, false-start flash red).
- **Audio (WebAudio):** Subtle tick on READY start, punchy beep on GO, success chime on result, buzzer on false start. All generated procedurally — no audio files needed.
- **Haptics:** `navigator.vibrate(30)` on GO and on false start where supported.

---

## 4. Progression

No unlock tree or leveling system. Progression is personal-record driven:
- localStorage stores `best_mode1`, `best_mode2`, `best_mode3` (ms values).
- Rating tiers provide a soft progression ladder (see Section 5).
- No prestige or reset mechanic.
- No gating — all modes available immediately.

---

## 5. Economy & RNG *(tables)*

### Currencies
None. [Confirmed — no economy, free game]

### Rating Tiers (Mode 1 — Classic Reaction) [Estimated from research benchmarks]
| Range (ms) | Label | Description |
|---|---|---|
| < 150 | Superhuman | Elite athlete / competitive gamer territory |
| 150–199 | Pro | Top 5% of population |
| 200–249 | Fast | Above average |
| 250–299 | Average | Normal healthy adult (~250 ms mean) |
| 300–399 | Slow | Below average |
| ≥ 400 | Very Slow | Check device/network latency |

**Research backing:** [Confirmed] Visual simple reaction time mean: ~250–293 ms (healthy adults); auditory ~248 ms. Elite athletes and F1 drivers routinely achieve 150–200 ms. False starts in athletics defined as < 100 ms (impossible to be a true reaction). Source: peer-reviewed studies cited in design research.

### RNG
- Mode 1: Uniform random delay between 1500 ms and 4500 ms per trial — `Math.random() * 3000 + 1500`.
- Mode 2: Target position: uniform random within safe area (excluding 60 px border). Target lifetime: fixed 3000 ms.
- Mode 3: Tile colors chosen from a set of 6 saturated colors; target color is one of those 6; at least one tile guaranteed to show the target color.
- No drop rates or gacha.

---

## 6. Content Inventory *(counts + lists)*

### Modes: 3
| # | Mode Name | Trials per Run | Key Metric |
|---|---|---|---|
| 1 | Classic Reaction | 5 | Average ms, best ms |
| 2 | Tap the Target | 10 targets | Hit %, avg ms per hit |
| 3 | Catch the Color | 8 rounds | Correct %, avg ms per correct |

### Colors Used
- Wait state: `#e74c3c` (red)
- Go state: `#2ecc71` (green)
- False start: `#c0392b` (deep red)
- Mode 3 tile palette: red, blue, yellow, purple, orange, teal

### Audio Assets (procedural WebAudio — no files)
- Tick sound (440 Hz, 50 ms)
- Go beep (880 Hz, 100 ms, sharp attack)
- Success chime (1047 Hz chord, 300 ms, soft decay)
- False start buzzer (200 Hz, 200 ms, sawtooth)
- Target pop (600 Hz, 60 ms)

---

## 7. Theme, Narrative & Tone

- **Setting/World:** Abstract, minimal. No story, no characters.
- **Premise:** Pure reflex measurement — clinical/sporty aesthetic.
- **Tone:** Energetic and clean. Bold color blocks, sharp feedback, competitive.
- **Visual theme:** Dark background (#0f0f1a), bright reactive panels, white/cream text. Influenced by F1 timing displays and sports performance apps.
- **Writing style:** Terse, data-focused labels ("Your time: 231 ms — Fast!"). No narrative text.
- **IP:** None. No licensed characters or brands.

---

## 8. Meta & Social Systems

### Personal Best
- localStorage per mode. Displayed prominently on summary screen. [Estimated]

### Sharing (Optional Enhancement)
- Copy-to-clipboard result text: "I scored 218 ms average on Reflex Rush Classic! Can you beat me?"
- No server-side leaderboard in the base build (single-file, offline).

### No:
- Missions / achievements
- Daily rewards
- Battle pass
- Live ops events
- Multiplayer / guilds

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|---|---|
| **Mode Select** | Landing screen; title, 3 mode cards with descriptions and best times, How to Play toggle |
| **Ready Screen** (Mode 1) | Large red panel, "Wait… Don't tap yet!" text, trial counter (e.g. "Trial 2/5") |
| **False Start Screen** | Red flash, "TOO EARLY! False start." message, brief 1.5 s pause then retry |
| **GO Screen** | Full green panel, "TAP NOW!" label — player presses here |
| **Trial Result** | Green panel persists, shows "238 ms — Fast!", auto-advances after 1 s |
| **Trial In Progress** (Mode 2) | Dark area with one circular target, miss/hit counter, time remaining bar |
| **Grid Screen** (Mode 3) | 3×3 tile grid, target color shown at top, round counter |
| **Summary Screen** | All trial times listed, average, session best, all-time best badge, rating, Retry / Change Mode |
| **How to Play Modal** | Overlays current screen; step-by-step instructions for active mode |
| **Settings / Mute** | Inline toggle (no separate screen); mute button in header |

### Settings Menu Contents
- Mute / unmute audio (inline button)
- No other settings in base build

### Gameplay HUD (Mode 1)
- Trial counter top-right (e.g. "3 / 5")
- State panel fills entire lower 70% of viewport
- Instructions text centered in panel

### Gameplay HUD (Mode 2)
- Hit / Miss counter top bar
- Remaining targets counter
- Target element (circle) positioned within play area

### Gameplay HUD (Mode 3)
- "Tap the: [COLOR SWATCH]" banner at top
- Round counter
- 3×3 grid of large, tappable tiles

### Navigation Flow
```
Mode Select → Ready Screen → [GO | FALSE START] → Trial Result
    (loop 5×) → Summary → [Retry same mode | Mode Select]
Mode Select → Tap Target play area → Summary
Mode Select → Catch Color grid → Summary
```

### Onboarding / Tutorial (Step-by-step)
1. First visit: How to Play modal auto-opens showing Mode 1 instructions.
2. Step 1: "Choose a mode from the cards below."
3. Step 2 (Mode 1): "A red screen will appear. Wait until it turns green."
4. Step 3: "Tap/click (or press Space) as fast as possible when green appears."
5. Step 4: "Don't tap early — that's a false start!"
6. Step 5: "After 5 trials, you'll see your average and rating."
7. Dismiss button: "Got it — Let's go!"
8. Mode 2/3 show brief one-liner instructions above the play area before first trial.

---

## 10. Art, Audio, Camera & Feel

- **Dimension:** 2D flat, no camera — pure DOM/CSS.
- **Perspective:** N/A (static UI).
- **Orientation:** Portrait-primary; responsive to landscape.
- **Art style:** Flat design, bold color blocks, minimal ornamentation.
- **Palette:** Dark navy background `#0f0f1a`, accent white `#f0f0f0`, red `#e74c3c`, green `#2ecc71`, gold `#f1c40f` (best-time badge), soft grey `#888`.
- **Typography:** System sans-serif stack; large bold numbers for the ms result (72–96 px on desktop, 48–64 px on mobile).
- **Animation:** CSS transitions on panel color (100 ms ease); target scale-up on spawn (100 ms); false start screen shake (CSS keyframe).
- **VFX:** GO flash (panel briefly over-brightens via CSS filter then settles); result number pop-in scale animation.
- **SFX:** All WebAudio procedural oscillators (see Section 6 audio list). 
- **Music:** None (would distract from focus/timing feel).
- **Juice:** Screen-edge pulse on false start; green shimmer on clean trial; gold banner on new best time; vibrate on mobile for GO signal.

---

## 11. Monetization

None. [Confirmed — this is a free, standalone, no-ads, no-IAP game.]
- No ads, no IAP, no subscriptions.
- No consent/ATT/CMP prompts required (no tracking, no ads).
- Privacy: no data leaves the device; only localStorage used.

---

## 12. Retention Hooks

- **Personal best persistence:** localStorage best time per mode. Every session shows "Your best: 218 ms" — motivates beating it.
- **Rating ladder:** Labeled tiers create aspiration to reach the next tier.
- **Consistency pressure:** 5-trial average means one lucky fast tap isn't enough; need sustained performance.
- **No offline/idle earnings** — pure skill game, none needed. [Confirmed]
- **No push notifications** — no server side. [Confirmed]
- **No energy/lives system** — unlimited replays. [Confirmed]

---

## 13. Localization & Accessibility

- **Language:** English only in base build. [Estimated]
- **RTL support:** Not implemented in base build.
- **Text scaling:** Large font sizes throughout; all critical text is 16 px minimum.
- **Colorblind support:** Color labels ("GREEN = GO") included in text, not only color alone; a future enhancement would offer a symbol-overlay mode.
- **Contrast:** Dark background + bright colored panels meets WCAG AA for text contrast.
- **Keyboard accessibility:** Space / Enter works for Mode 1 reaction. Tab navigation possible for buttons.
- **Touch:** Large touch targets (min 44 px per Apple HIG); no pinch/zoom required.
- **Reduced motion:** Reactions to `prefers-reduced-motion` — screen shake / flash animations skipped if enabled. [Estimated — add in build]
- **Age/content rating:** IARC 3+ (all audiences). [Estimated]
- **COPPA/GDPR-K:** No data collection at all, so compliance is trivially satisfied. [Confirmed by design]

---

## 14. Technical Structure

- **Engine/framework:** Vanilla HTML5 + CSS3 + JavaScript (ES6+). No frameworks, no build step.
- **Platform:** Any modern browser; offline-capable (single file, no network requests).
- **Save system:** `localStorage` only; keys: `rr_best_mode1`, `rr_best_mode2`, `rr_best_mode3`.
- **Timing:** `performance.now()` for sub-millisecond precision timestamps. [Confirmed — standard for browser reaction tests]
- **Audio:** WebAudio API (AudioContext, OscillatorNode, GainNode). All SFX generated in code.
- **Accounts/auth:** None.
- **Multiplayer/netcode:** None.
- **Anti-cheat/server authority:** N/A (single-player, client-side only). [Confirmed]
- **Analytics:** None.
- **Remote config:** None.
- **Third-party SDKs:** None.
- **App size:** Single .html file, ~25–40 KB uncompressed. [Estimated]
- **Performance:** No canvas rendering; pure DOM — 60 fps on any device made after 2015.
- **Compatibility:** Chrome 70+, Firefox 65+, Safari 12+, Edge 79+, iOS Safari 12+, Android Chrome 70+.

---

## 15. Pacing & Difficulty

### Mode 1 — Classic Reaction (5 trials)
- **Trial 1:** Orientation — player learns the red→green state.
- **Trials 2–3:** First false start often occurs here as anticipation builds. Player recalibrates.
- **Trials 4–5:** More relaxed, genuine reaction. Average typically settles 240–280 ms for new players.
- **Churn point:** Players with repeated false starts may feel frustrated; the penalty message must be encouraging ("Oops! Try to wait...") not punishing.

### Mode 2 — Tap the Target (10 targets)
- Early targets appear in center-friendly positions [Estimated — edge-weighted spawning could be harder].
- Targets shrink slightly in later rounds to add challenge. [Estimated — optional]
- "Miss" on target 7+ creates urgency.

### Mode 3 — Catch the Color (8 rounds)
- Rounds 1–2: Only 3 distinct colors in grid — easier identification.
- Rounds 3–5: 5–6 colors in grid — higher cognitive load.
- Rounds 6–8: Tile shuffle animation shortened to increase pressure.
- Wrong taps punished by +200 ms penalty rather than void — encourages deliberate speed over random tapping.

### Difficulty Curve
No adaptive difficulty in base build. The random delay in Mode 1 (1.5–4.5 s) is the core anti-anticipation mechanism. A veteran who truly improves will see their average drop into the 200–220 ms range with practice. [Estimated]

---

## 16. Clone Build Plan

### MVP Feature Set
- [x] Mode 1: Classic Reaction (5-trial run, red/green state, false-start detection, average + best)
- [x] Mode 2: Tap the Target (10 targets, hit/miss, avg ms)
- [x] Mode 3: Catch the Color (8 rounds, cognitive-reflex hybrid)
- [x] Summary screen with rating tier
- [x] localStorage personal best per mode
- [x] WebAudio procedural SFX + mute toggle
- [x] Touch + keyboard controls
- [x] Responsive mobile-first layout

### Full Feature Set (Beyond MVP)
- [ ] Online leaderboard (requires backend)
- [ ] User accounts + cross-device sync
- [ ] Mode 4: Audio Reaction (react to beep, not visual)
- [ ] Mode 5: Sequence memory + reflex
- [ ] Colorblind symbol overlay
- [ ] RTL localization
- [ ] Social share with OG card generation
- [ ] Daily challenge (server-seeded random delays)

### Phased Roadmap

**Phase 1 — Core (this build)**
- Single HTML file with all 3 modes
- State machine per mode
- `performance.now()` timing
- False start logic
- 5/10/8 trial series
- Summary + rating
- localStorage best times
- WebAudio SFX
- Responsive CSS

**Phase 2 — Polish**
- Micro-animations and VFX refinement
- `prefers-reduced-motion` support
- Colorblind text labels
- Share button (copy to clipboard)

**Phase 3 — Social**
- Backend API for global leaderboard (Node.js + SQLite or Supabase)
- User auth (guest tokens or OAuth)
- Daily seeded challenge

### Recommended Tech Stack
- **Base:** Vanilla HTML/CSS/JS (no dependencies)
- **Phase 3 backend:** Node.js + Express + Supabase (PostgreSQL)
- **Hosting:** Any static host (GitHub Pages, Netlify, Vercel, Cloudflare Pages)

### Required Asset List
| Asset | Source |
|---|---|
| All SFX | Procedural WebAudio (generated in code) |
| Fonts | System stack (no external fonts) |
| Icons | Unicode symbols / CSS shapes only |
| Background / panels | CSS colors only |

### Hardest Parts / Risks
1. **Input latency on mobile:** TouchStart events on iOS Safari can have a 300 ms artificial delay on click events; must use `touchstart` + `preventDefault()` for the reaction area, not `click`.
2. **False start UX:** The penalty state must clear all pending timers cleanly to avoid ghost GO events firing after a false start. Rigorous timeout ID tracking required.
3. **Mode 3 color ambiguity:** With 6 colors in a 3×3 grid, some combinations can make identification hard on small screens. Color saturation and tile size must be tuned carefully.
4. **WebAudio context unlock:** iOS requires a user gesture to unlock AudioContext. Must resume context on first user interaction, not on page load.

---

## 17. Open Questions

1. **Adaptive difficulty for Mode 1:** Should the random delay window narrow as the player improves (e.g., 2–4 s after average < 250 ms)? Needs playtesting to determine if it adds fun or frustration. [Estimated: keep fixed for MVP]
2. **False start threshold:** Using "any press before GO" as the threshold. An alternative would be a minimum 100 ms after GO to also flag impossibly fast presses. Could be added as an optional strict mode.
3. **Mode 2 target size scaling:** Whether smaller targets in later rounds noticeably improves fun vs. frustration balance needs user testing.
4. **Audio timing offset:** The GO audio beep plays at the same time as the green visual; some platforms may have slight audio latency. If measured, the visual should be the canonical go signal, not the audio — this is already the design intent but worth validating on iOS.

---

*Blueprint version 1.0 — built for Game #50 of the web game series. All mechanics are original designs; no copyrighted names, assets, or text from any existing reaction test product are reproduced.*
