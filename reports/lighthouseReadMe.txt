What is Lighthouse?
Lighthouse is an open-source, automated auditing tool built by Google for improving web quality.
It analyzes a given webpage (in this case, the local JuiceBox app) and provides detailed reports covering key categories like Performance, Accessibility, Best Practices,  and Progressive Web App metrics.

What This Report Shows

Accessibility Audit
Validates that the JuiceBox UI can be used by all users, including those relying on assistive technologies:

It validates things like:
• All images have descriptive alt attributes
• Proper color contrast between text and backgrounds.
• Interactive elements (buttons, links) have visible and programmatic labels.
• Proper heading structure (<h1> → <h6> hierarchy).
• Elements use correct ARIA roles and attributes.
• Keyboard navigability (no focus traps).
• <html lang=""> attribute is defined and valid.
• No duplicate ARIA IDs or invalid aria-* attributes.

Performance Audit
• Measures your site’s speed and user experience on load:
• First Contentful Paint (FCP): how fast something appears.
• Largest Contentful Paint (LCP): how fast the main content appears.
• Cumulative Layout Shift (CLS): visual stability of the page.
• Total Blocking Time (TBT): responsiveness during loading.
• Time to Interactive (TTI): when it becomes fully usable.
• Flags inefficient images, unused CSS/JS, or missing compression (GZIP/Brotli).
• Checks for render-blocking scripts, caching policy, and font loading optimization.

Final Output
•  lighthouse-report.html — visual interactive dashboard (with charts, scores, improvement suggestions).
•  lighthouse-report.json — machine-readable data if you ever want to parse it into Serenity or dashboards later.

Each category is scored 0–100, so you can instantly benchmark build-to-build improvements.

How to Run This Report for Juicebox
Run from the project root:
lighthouse http://localhost:3000 ^
  --only-categories=accessibility,performance ^
  --chrome-flags="--headless" ^
  --output html ^
  --output json ^
  --output-path "C:\YourFolder\JuiceBox-Automation\reports\lighthouse-report"

Option 2 — One-Click Script
You can also execute the bundled batch file:
scripts\run_lighthouse.bat
This automatically timestamps the output and stores both reports under:
/reports/lighthouse/<timestamp>/

Report Location
Default output (if run manually):
C:\YourFolder\JuiceBox-Automation\reports\
│
├── lighthouse-report.html
├── lighthouse-report.json
└── lighthouseReadMe.txt


