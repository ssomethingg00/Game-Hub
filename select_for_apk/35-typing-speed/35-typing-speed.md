# Typing Speed Game — Clone Blueprint

> Note: This blueprint targets an original-branded clone. Replicate mechanics & systems only —
> not any specific product's name, logo, or copyrighted art/audio/text.

---

## 1. Snapshot

A browser-based typing speed trainer that presents a passage of text and measures how fast and accurately the player can type it. The player types the passage word-by-word with live per-character color feedback; a results screen delivers WPM, raw WPM, accuracy %, and error count. Personal bests are saved locally. The genre is **skill/training tool**; it sits between pure game and utility — the appeal is the measurable, improvable performance metric and the immediate tactile feedback loop.

**Quick facts:**
- Developer: original build (inspired by Monkeytype, TypeRacer, and KeyHero) [Estimated]
- Platform: browser (desktop + mobile)
- Release: new build, June 2026
- Age/content rating: IARC 3+ (neutral text passages, no violence or mature content) [Estimated]
- Monetization: none (free, single-player, offline) [Estimated]
- Session length: 15 seconds to ~3 minutes depending on mode

---

## 2. Core Loops

- **30-second loop:** Player reads a word or character ahead, types it, sees instant green/red feedback per character, cursor advances; correct = green, wrong = red, current = highlighted caret; backspace corrects; the live WPM counter ticks every second.
- **Session loop:** Select mode (timed 15/30/60s or fixed passage) → read passage → type until time runs out or passage complete → results screen shows WPM, raw WPM, accuracy %, errors, personal best → restart with fresh text.
- **Meta loop:** Each session updates the personal-best WPM/accuracy per mode stored in localStorage; the player chases their own record across visits; no external leaderboard in MVP.

---

## 3. Mechanics, Controls & Game States

### Core Mechanics
- **Text render:** Passage displayed character-by-character in a fixed-width monospace zone; each character is a `<span>` with a CSS class (pending / correct / incorrect / cursor).
- **Typing capture:** A real `<input type="text">` or `<textarea>` that stays focused behind the display (invisible or transparent overlay approach); keystrokes are captured on `input`/`keydown` events.
- **Live character coloring:** On every keystroke, typed characters are compared to expected characters; spans update classes in real time.
- **Backspace:** Removes the last typed character; the corresponding span reverts from correct/incorrect to pending; error count for that position is cleared.
- **Timer start:** Timer begins on the first real character keystroke (not on focus or whitespace alone).
- **Timer end (timed mode):** Countdown from selected duration (15/30/60s); when it hits 0, typing is disabled and results screen appears.
- **Completion (passage mode):** When the last character of the passage is typed, timer stops and results screen appears.
- **Text refresh:** In timed mode with a short duration, new words are appended or the buffer scrolls as the player reaches the end of visible text.
- **WPM formulas:**
  - Net WPM = (correctly typed characters / 5) / elapsed minutes [Confirmed]
  - Raw WPM = (total typed characters / 5) / elapsed minutes [Confirmed]
  - Accuracy % = (correct characters / total typed characters) × 100 [Confirmed]
  - Error count = total wrong-character keystrokes committed (not corrected at moment of typing)
- **Word-level highlighting:** In addition to per-character color, the current-word boundary is visually marked.

### Game Modes
| Mode | Description |
|---|---|
| Timed 15s | Type as many words as possible in 15 seconds |
| Timed 30s | Type as many words as possible in 30 seconds |
| Timed 60s | Type as many words as possible in 60 seconds |
| Passage | Type a fixed ~100-word paragraph; no time limit; measures completion time |

### Core Verbs
- Type, correct (backspace), start, restart, select mode

### Input Scheme
- **Primary:** Physical keyboard (desktop). `keydown` / `input` events on a real `<input>` element.
- **Mobile:** `<input>` element is auto-focused on tap of the passage area; `inputmode="text"` triggers the device soft keyboard. Typing maps character-by-character exactly as on desktop.
- **Orientation:** Portrait-first (mobile); works in landscape; desktop sees wider passage layout.

### Win / Lose / Fail States
- **Completion:** Timer expires or passage finished → results screen (no "lose" state; all outcomes are scored).
- **Restart:** Available at any time; resets timer, clears colors, loads new text.
- **No failure state:** Accuracy below a threshold is noted on the results screen but does not block progress.

### Difficulty Modes
- Mode length (15/30/60s) doubles as difficulty (shorter = harder to achieve high WPM due to ramp time). [Estimated]
- Passage mode uses varied difficulty word pools: easy (common 200 words), medium (common 1000 words), hard (includes less-common words + punctuation). [Estimated]

### Feedback Systems
- **Visual:** Character spans change color instantly on each keystroke.
- **Audio:** Subtle per-keystroke tick (WebAudio generated), louder error click on wrong character, finish chime on completion. Mute toggle available.
- **Error "shake":** Tiny CSS shake animation on the passage container on an incorrect keystroke.
- **Live HUD:** WPM and accuracy update every 500ms during typing.

### AI / Opponent Behavior
- None in MVP. (A ghost cursor showing the player's previous-best pace could be a v2 feature.) [Estimated]

---

## 4. Progression

### Personal Bests (localStorage)
- Best WPM stored per mode key: `"timed-15"`, `"timed-30"`, `"timed-60"`, `"passage"`.
- Best accuracy stored alongside best WPM per mode.
- Best is updated only when the new score is strictly higher.
- Displayed on results screen: "Personal Best: 87 WPM" with a trophy icon if current run beats it.

### No XP / Level / Unlock Trees
- This genre is skill-based, not progression-locked. [Confirmed — genre norm]
- All modes are available from first load.

### Prestige / Reset
- None. Player can manually clear localStorage if desired (settings option).

### Gating
- None. All content unlocked immediately.

---

## 5. Economy & RNG

### Currencies
None. This game has no economy or currency system. [Confirmed — genre norm]

### RNG
| Element | Mechanism |
|---|---|
| Passage selection | Random pick from a pool of 30+ passages / word lists per difficulty tier; seeded by `Math.random()` |
| Word pool shuffle | In timed mode, words are drawn randomly from a curated frequency list (top 200 most common English words) |

### No drop rates, gacha, loot tables, or upgrades.

---

## 6. Content Inventory

### Passages (built-in, no network fetch)
| Type | Count | Example sources |
|---|---|---|
| Common-word timed pool | 200 words | Top 200 English frequency words |
| Short paragraphs | 10 passages | Pangrams, famous quotes (original phrasing) |
| Medium paragraphs (~80 words) | 10 passages | Original prose covering varied topics |
| Hard paragraphs (punctuation-heavy) | 10 passages | Includes commas, colons, hyphens |

Total inline text: ~6,000 characters embedded in the HTML file.

### UI Screens
- Mode/duration selector
- Typing arena (main gameplay view)
- Results modal
- How-to tooltip/overlay

### Audio Assets
- All generated via WebAudio API (no external files):
  - Key click (short oscillator burst, ~20ms)
  - Error click (dissonant tone, ~30ms)
  - Finish chime (ascending 3-note melody, ~400ms)

---

## 7. Theme, Narrative & Tone

- **Setting:** Minimal, dark-mode terminal aesthetic. No story, no characters.
- **Visual theme:** Deep navy/charcoal background, soft gray passage text, green for correct, red for incorrect, amber/gold for cursor position. Monospace font (system: `'Courier New', Courier, monospace`).
- **Tone:** Calm, focused, precise. The design should not distract from typing. Every animation is subtle.
- **Narrative:** None. The "story" is the player's personal progress.
- **Licensed IP:** None.

---

## 8. Meta & Social Systems

### Personal Best Board (local)
- Per-mode best WPM + accuracy stored in localStorage.
- Shown on results screen and in a small corner badge during gameplay.

### Daily Challenge (v2 suggestion)
- A fixed daily passage (seeded by date) for consistent comparison. [Estimated, not in MVP]

### Multiplayer / Leaderboards
- None in MVP. [Estimated]

### Achievements
- None in MVP; could add "First 60 WPM", "100% Accuracy run" badges in v2. [Estimated]

### Live-ops Cadence
- No live-ops needed (static word pools, no server).

---

## 9. UI / UX & Screen Map

### Screen List
| Screen | Purpose |
|---|---|
| Main / Game Screen | Mode selector + passage area + HUD; single-page app |
| Results Modal | Overlay showing WPM, raw WPM, accuracy, errors, personal best; restart button |
| How-To Overlay | Brief instructions shown on first visit or via ? button |
| Settings Panel | Mute toggle, theme option (v2), clear best scores |

### Gameplay HUD Elements
- Live WPM (updates every 500ms)
- Live accuracy %
- Countdown timer (timed modes) or elapsed time (passage mode)
- Error count
- Personal best badge (top-right)
- Mute toggle button
- Mode selector tabs (15s / 30s / 60s / Passage)
- Restart button (icon + keyboard shortcut: Tab or Ctrl+Enter)

### Navigation Flow
```
[Load page]
    ↓
[Main Screen — mode tabs visible, passage rendered, input focused]
    ↓ (first keystroke)
[Timer starts; HUD activates]
    ↓ (timer ends or passage complete)
[Results Modal overlays]
    ↓ (Restart button or Tab key)
[Main Screen resets with new passage]
```

### Onboarding (First-Time User Flow)
1. Page loads with a brief tooltip: "Click the text or press any key to start typing."
2. Mode tabs are visible; default is 30s timed.
3. On first keystroke, tooltip fades and timer starts.
4. On first session completion, results modal explains each metric with a tooltip label.
5. No forced tutorial; the interface is self-explanatory.

### Settings / Options Menu Contents
- Mute / unmute sound
- Clear personal bests (with confirmation)
- Keyboard shortcut reference (Tab = restart, Esc = cancel)

---

## 10. Art, Audio, Camera & Feel

- **Dimension:** 2D; no camera; static layout.
- **Perspective:** N/A (text UI).
- **Orientation:** Portrait-primary (mobile), adapts to landscape and desktop wide.
- **Art style:** Flat, minimal, monochrome with accent colors. No illustrations.
- **Color palette:**
  - Background: `#1a1b26` (deep navy)
  - Surface: `#24283b`
  - Passage text (pending): `#565f89`
  - Correct: `#9ece6a` (soft green)
  - Incorrect: `#f7768e` (soft red)
  - Cursor: `#e0af68` (amber)
  - Accent / HUD: `#7aa2f7` (blue)
  - White text: `#c0caf5`
- **Typography:** System monospace (`'Courier New', Courier, monospace`); 18–22px on desktop, 16px on mobile; line-height 1.8 for readability.
- **Animations:**
  - Character color transition: `transition: color 0.05s`
  - Error shake: `@keyframes shake` 3-frame lateral jitter, 150ms
  - Results modal: fade-in `opacity` transition 200ms
  - Cursor blink: CSS `animation: blink 1s step-end infinite`
- **VFX:** None beyond color transitions and shake.
- **SFX (WebAudio generated):**
  - Key click: `OscillatorNode`, type `sine`, freq 800Hz, gain envelope 0→0.1→0 over 20ms
  - Error: `OscillatorNode`, type `sawtooth`, freq 200Hz, gain 0→0.15→0 over 30ms
  - Finish chime: 3 oscillators in sequence (C5→E5→G5), 100ms each, sine wave
- **Music:** None.
- **Haptics:** Not implemented (browser API limited; device may provide native haptic on virtual keyboard taps).
- **Juice:** The combination of instant color feedback, error shake, and tick sounds creates strong tactile feel without visual clutter.

---

## 11. Monetization

**None.** This is a free, offline, single-player tool with no ads, IAP, or tracking.

- No ads, no banners, no interstitials.
- No IAP.
- No subscriptions.
- No consent/ATT/CMP flow required (no data collection, no tracking).
- No age gate required (IARC 3+, no data collection).

---

## 12. Retention Hooks

- **Personal best:** The primary retention driver — players return to beat their own score.
- **Mode variety:** Four durations keep sessions fresh; 60s feels like a "full run" vs 15s "sprint".
- **Random passages:** Each restart draws new text from the pool, preventing memorization.
- **No idle/offline earnings:** Not applicable to this genre; skill improvement is the only "reward".
- **No push notifications:** N/A for browser tool without service worker.
- **No energy/lives system:** Unlimited retries.
- **FOMO:** None intentional; the game is designed for intrinsic motivation.

---

## 13. Localization & Accessibility

### Localization
- **Language:** English only in v1. [Estimated]
- **RTL support:** Not needed for English. Would require passage reflow for Arabic/Hebrew. [Estimated v2]
- **Regional differences:** None.

### Accessibility
- **Text scaling:** Uses `em`/`rem` units; browser zoom works correctly.
- **Colorblind mode:** v1 relies on color only (green/red). v2 should add shape cues (underline for incorrect, bold for correct). [Estimated]
- **Keyboard-only:** Fully keyboard-operable (all controls reachable by Tab/Enter/keyboard shortcuts).
- **Screen reader:** The live typing arena is not screen-reader-friendly by design (constant DOM mutation); this is a known limitation of the genre. [Confirmed — genre norm]
- **High contrast:** Dark palette has sufficient contrast for most users; WCAG AA for HUD text.
- **Difficulty assist:** Passage mode allows unlimited time; no forced time pressure.

### Age / Content Rating
- IARC 3+ / PEGI 3 equivalent. No violence, no mature content, no data collection.
- COPPA/GDPR-K compliance: no data collected at all (only localStorage, no server).

---

## 14. Technical Structure

### Engine / Framework
- Vanilla JavaScript (ES6+), no framework, no build step.
- Single `.html` file with inline `<style>` and `<script>`.

### Platforms
- Any modern browser: Chrome 90+, Firefox 88+, Safari 14+, Edge 90+, mobile Safari/Chrome.
- Offline-capable: double-click to open locally; no network required.

### Save System
- `localStorage` only. Keys: `pb_timed15`, `pb_timed30`, `pb_timed60`, `pb_passage` (each storing `{wpm, accuracy, date}`).
- No accounts, no cloud sync, no login.

### Multiplayer / Netcode
- None. Single-player only.

### Anti-Cheat / Server Authority
- N/A (single-player, no server, no leaderboard).

### Backend Services
- None. Fully static.

### Analytics
- None. No tracking SDK.

### Third-party SDKs
- None.

### App Size
- Single HTML file, target < 50 KB. [Estimated]

### Performance Notes
- DOM mutations on every keystroke: use class toggling on pre-rendered spans (not innerHTML rewrites) to keep reflow minimal.
- `requestAnimationFrame` or `setInterval(500ms)` for live WPM update.
- WebAudio context created on first user gesture to comply with autoplay policies.

---

## 15. Pacing & Difficulty

### Early Game (first session)
- Player sees the passage, presses a key, timer starts — immediate engagement.
- 30s default feels accessible; most players type 30–50 WPM, completing ~15–25 words.
- Error feedback is instant and non-punishing; backspace always works.

### Mid Game (sessions 2–10)
- Player notices personal best badge; motivation to beat it emerges.
- They experiment with 60s mode for a "real" score.
- Accuracy starts to improve as they slow down to avoid red characters.

### Late Game (regular player)
- Player hunts for 60+ WPM; mode switch to harder passages adds challenge.
- The "hard" passage mode with punctuation creates a meaningful skill jump.
- No artificial ceiling; skill ceiling is human maximum (~180+ WPM for elite typists).

### Key Milestone Moments
1. First completion of a timed test → first WPM score (aha: "I can measure myself")
2. First personal best beaten → score updates with highlight
3. First 60 WPM → significant psychological milestone [Confirmed — typing community norm]
4. Sub-5% error rate run → accuracy achievement

### Churn Points
- Players who dislike the text content may leave; diverse passage pool mitigates this.
- Mobile players may churn if the virtual keyboard doesn't map correctly — must be tested.
- No forced onboarding = some players may be confused by no visible "start" button; mitigated by tooltip.

---

## 16. Clone Build Plan

### MVP vs Full Feature Checklist

| Feature | MVP | Full |
|---|---|---|
| Timed modes (15/30/60s) | ✓ | ✓ |
| Passage mode | ✓ | ✓ |
| Live per-character highlighting | ✓ | ✓ |
| WPM / raw WPM / accuracy / errors | ✓ | ✓ |
| Results screen | ✓ | ✓ |
| Personal best (localStorage) | ✓ | ✓ |
| Restart with new text | ✓ | ✓ |
| Mobile input focus | ✓ | ✓ |
| WebAudio SFX | ✓ | ✓ |
| Mute toggle | ✓ | ✓ |
| Responsive / mobile-first | ✓ | ✓ |
| How-to tooltip | ✓ | ✓ |
| Colorblind mode | — | v2 |
| Ghost cursor (pace ghost) | — | v2 |
| Daily challenge passage | — | v2 |
| Multiple languages | — | v2 |
| Custom passage input | — | v2 |
| Online leaderboard | — | v3 |
| Account / cloud sync | — | v3 |

### Phased Roadmap

**Phase 1 — Core engine (1–2 days)**
- Render passage as individual `<span>` elements
- Capture input via hidden `<input>`; compare to expected chars
- Assign `correct` / `incorrect` / `cursor` CSS classes in real time
- Backspace removes last typed char and reverts class

**Phase 2 — Timer & scoring (half day)**
- `setInterval` countdown (timed mode) and elapsed clock (passage mode)
- Start on first keystroke; stop on timer-end or passage-complete
- Compute net WPM, raw WPM, accuracy %, error count

**Phase 3 — Results & persistence (half day)**
- Results modal overlay; animate in
- localStorage read/write for personal bests
- "New best!" highlight when beaten

**Phase 4 — Content & modes (1 day)**
- Embed 200-word common pool + 30 passages inline
- Mode selector tabs; switch refreshes passage and resets state
- Timed mode: append new words when player nears end of rendered text

**Phase 5 — Polish & mobile (1 day)**
- Responsive CSS; `<meta viewport>`; font scaling
- Mobile input: `<input>` auto-focus on tap; `inputmode="text"`
- WebAudio SFX; mute toggle
- Error shake animation
- How-to tooltip (first visit via localStorage flag)

**Phase 6 — QA (half day)**
- Timer start/stop edge cases
- Backspace at position 0
- Mode switch mid-test
- Mobile Safari/Chrome physical typing test
- File ends with `</html>`; no console errors

### Recommended Tech Stack
- HTML5 + CSS3 + ES6 vanilla JavaScript
- No frameworks, no bundlers, no CDN dependencies
- WebAudio API for SFX
- localStorage for persistence
- `<meta name="viewport" content="width=device-width, initial-scale=1">` for mobile

### Required Asset List
- No external assets
- Text content: embedded word pool and passage strings in JS constant
- Colors: CSS custom properties (variables)
- Fonts: system monospace stack

### Hardest Parts / Risks
1. **Mobile input mapping:** The hidden `<input>` focus trick must survive Safari's aggressive blur behavior; an `ontouchstart` re-focus listener is needed.
2. **Timed mode text buffer:** When a player types faster than the visible passage, new words must be appended seamlessly without losing highlight state.
3. **WPM live accuracy:** Updating WPM every 500ms during the first few seconds produces wild swings; clamp to "0" until at least 2 seconds have elapsed.
4. **Backspace across word boundaries:** Must handle backspace into the previous word correctly in passage mode.
5. **WebAudio autoplay policy:** Context must be created inside a user-gesture handler (keydown), not on page load.

---

## 17. Open Questions

1. Should the error count reflect "errors at time of typing" (including self-corrected ones) or "final uncorrected errors"? Standard practice is to count errors at the moment they occur (corrected or not) for the accuracy metric, but show final uncorrected errors in the error count. [Needs design decision — currently spec'd as: accuracy uses all errors, error count uses uncorrected final errors.]
2. What word pool size is "enough" for timed mode before repetition becomes noticeable? 200 words is estimated; 1000 is better for experienced players. [Estimated; tune by playtesting.]
3. Mobile haptic feedback: browsers have limited haptic API access; native soft-keyboard haptics are device-controlled and cannot be relied upon.
