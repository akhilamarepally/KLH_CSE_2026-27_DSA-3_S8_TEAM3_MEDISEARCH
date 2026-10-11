PROJECT TITLE:MEDISEARCH
TEANM NAMES
AKHILA-2520030511
SAHASRA-2520030108
TEJASWINI-2520030510

MediSearch
Intelligent Hospital Patient Information Retrieval System
DSA-3 (25CS2103E) · A.Y. 2026-2027 · K L (Deemed to be) University
MediSearch is a frontend-only React application that organises 150 fictional patient records and retrieves them with exact, partial and approximate matching. It is built on classical string-matching, dynamic-programming and similarity algorithms, and stores data in the browser using IndexedDB.
> **Disclaimer:** All patient records are fictional. MediSearch is an academic demonstration and is not for clinical use.
Team
Name	Roll No.
Akhila	2520030511
Sahasra	2520030108
tejaswini	2520030510
Guide: Dr.VSireesha, Department of CSE
Features
Dashboard: live counts and disease distribution from stored records
Patient Search: by ID, name, symptom, doctor or medicine (exact → partial → approximate)
Autocomplete: Trie-based suggestions for names and IDs
Directory & Profile: browse, filter and open a complete patient profile
Add / Edit / Delete: validated form, with confirmation before delete
Clinical Records: prescriptions, medicine administration, lab reports, appointments
Activity History: audit events for registered, updated, searched and viewed
Similar Cases: ranks patients by symptom overlap using Jaccard similarity
Algorithm Lab: run Naive, KMP, Z, Rabin–Karp and Needleman–Wunsch on editable text and pattern
Import / Export: JSON backup and import, CSV directory export
How search works
Search runs in five stages and stops at the first stage that returns results.
Stage	Match	Method
1	Exact ID	HashMap lookup, O(1) average
2	Exact name	Case-insensitive equality
3	Name match	Every term found in the name (substring / KMP)
4	Partial match	Every term found anywhere in the record
5	Approximate	Edit distance ≤ max(1, ⌊0.2·len⌋), name only
Example: searching `Kavya Patl` returns `Kavya Patel` (edit distance 1).
Tech stack
Component	Technology
Language	JavaScript (ES6+) with JSX; supplementary Java demo
UI	React 18
Routing	React Router 6
Build tool / dev server	Vite 6
Storage	IndexedDB (stores: `patients`, `events`)
Data formats	JSON seed and import/export; CSV directory export
Testing	Node.js built-in test runner
Architecture
```
User → React UI + Router → Services layer → Algorithm modules → IndexedDB
```
Pages (15): Dashboard, Search, Directory, Profile, forms, Algorithm Lab
Services: `patientService`, `searchService`, `historyService`, `storageService`
Data path: seed JSON → IndexedDB → services → pages. The 150-record seed is loaded only when the IndexedDB store is empty, so edits survive a refresh but do not sync between browsers.
DSA concepts used
Category	Concepts
Data structures	HashMap, Trie, Set, Graph (adjacency list)
String matching	Naive, KMP, Z algorithm, Rabin–Karp, Boyer–Moore, Aho–Corasick
DP and similarity	Levenshtein, Needleman–Wunsch, Jaccard
In the UI: HashMap, Trie, KMP, Levenshtein, Jaccard, and the Algorithm Lab (Naive, KMP, Z, Rabin–Karp, Needleman–Wunsch).
Modules only (not yet wired into pages): Boyer–Moore, Aho–Corasick, BFS / DFS.
Getting started
Prerequisites: Node.js 20 or later, and npm.
```bash
# install dependencies
npm install

# start the development server
npm run dev

# create a production build
npm run build

# run the automated tests
node --test
```
Dataset
150 fictional patients (IDs `P0001` to `P0150`)
37 currently admitted, across 10 departments
600 related records: 150 each of prescriptions, lab reports, appointments and history notes
Testing
15 of 15 automated tests pass with the Node.js test runner. They cover unique IDs, all string-matching and similarity algorithms, the Trie, graph traversal, the HashMap index, and exact, partial, approximate and multi-term search.
A full-text query over 150 records took about 2 ms in Node.js (measured in the build sandbox).
Still to be verified locally: the production build, the browser UI walkthrough, IndexedDB persistence after refresh, file downloads and import, and responsive layout.
Limitations
Frontend only: no backend, shared database or real authentication
Data is local to one browser and fictional
Search scans all records; there is no inverted index yet
Aho–Corasick and BFS / DFS are modules, not yet wired into pages
Future scope
Secure login and role-based access
Shared database and REST API
Inverted index and BK-tree for large datasets
Multi-keyword search with Aho–Corasick
Phonetic and Damerau–Levenshtein matching
Automated UI tests and encryption
Complexity reference
n = text length, m = pattern length, z = number of matches, V and E = graph vertices and edges.
Algorithm	Time	Space
HashMap index	O(1) average lookup; O(N) build	O(N)
Naive search	O(n·m)	O(1)
KMP	O(n + m)	O(m)
Z algorithm	O(n + m)	O(n + m)
Rabin–Karp	O(n + m) expected; O(n·m) worst	O(1) extra
Boyer–Moore (bad character)	O(n/m) best; O(n·m) worst	O(σ)
Aho–Corasick	O(n + Σ|P| + z)	O(Σ|P|)
Levenshtein distance	O(m·n)	O(m·n)
Needleman–Wunsch	O(m·n)	O(m·n)
Jaccard similarity	O(|A| + |B|)	O(|A| + |B|)
BFS / DFS	O(V + E)	O(V)
