# 📘 Ceylonicus Compiler Rewrite Guide (Mentor Log)

## Project Context
- **Language name:** Ceylonicus (RezSat)
- **Original implementation:** Python, interpreted
- **Goal:** Rewrite as a **compiled language**
- **Frontend:** C
- **Backend:** LLVM
- **Platform:** Windows (GCC)
- **Encoding:** Source files must be UTF-8 (normalize to UTF-8 if needed)
- **Mentoring style:**
    - Explain concepts clearly
    - No full code dumps unless explicitly asked
    - Incremental, compiler-engineering-style approach

---

## High-Level Compiler Plan (Option B)

**Traditional compiler pipeline:**

```
Source (.cyl, UTF-8)
   ↓
Lexer (C)
   ↓
Parser (C)
   ↓
AST (C structs)
   ↓
LLVM IR generation
   ↓
Object code / executable
```

We are currently **only in the Lexer foundation stage**.

---

## Learning Strategy (Why This Order)

We are building the compiler in _vertical slices_, not all at once.

1. Define **tokens** clearly
2. Build **UTF-8-aware lexer**
3. Then parser, AST, LLVM later

This mirrors real compiler projects (Clang, Rust, Zig).

---

## ✅ What Has Been Done So Far

### 1️⃣ `token.h` — COMPLETED (Lexer Foundation)

You have successfully implemented:
#### a) Include guards

Prevents multiple definition errors.

```c
#ifndef CEYLONICUS_TOKEN_H
#define CEYLONICUS_TOKEN_H
...
#endif
```

#### b) `TokenType` enum

C equivalent of Python’s `Tokens = { ... }` dictionary.

Each token kind (INT, FLOAT, PLUS, EQEQ, KEYWORD, STRING, etc.) is represented as an enum value.

#### c) `Position` struct

C equivalent of Python’s `Position` class.

Purpose:
- Track **byte index** (important for UTF-8)
- Track line and column
- Used for error reporting and debugging

Uses `size_t` instead of `int` (correct for indices).

#### d) String slice (`StrSlice`)

Instead of copying strings, tokens **reference the original source buffer**.

Concept:

```
(ptr, length)
```

Why:

- No per-token memory allocation
- UTF-8 safe
- Faster and simpler
- Perfect for lexing/parsing pipelines

This replaces Python’s automatic string objects.

#### e) `TokenValue` union

C replacement for Python’s dynamic `value`.

Python:

```py
Token(INT, 123)
Token(FLOAT, 3.14)
Token(STRING, "hello")
```

C:

```c
union TokenValue {
    int64_t int_value;
    double float_value;
    StrSlice text;
};
```

Only **one** of these is valid depending on `TokenType`.

#### f) `Token` struct

C equivalent of Python’s `Token` class.

Contains:

- `TokenType type`
- `Position start`
- `Position end`
- `TokenValue value`

At this point, **the lexer has a complete target output format**.

✅ This is a major milestone.

---

## 🔁 Mapping to Original `lexer.py`

|Python (`lexer.py`)|C (`token.h`)|
|---|---|
|`Token(type, value, pos_start, pos_end)`|`struct Token { type, value, start, end }`|
|Python `int`, `float`|`TokenValue.int_value`, `TokenValue.float_value`|
|Python `string`|`StrSlice (ptr + len)`|
|Python `Position`|C `Position`|

Example mapping:

Python:

```py
return Token(Tokens['INT'], int(num_str), pos_start, self.pos)
```

C conceptually:

```
token.type = TOK_INT
token.value.int_value = parsed_integer
token.start = start_pos
token.end = end_pos
```

---

## ⏭️ What Comes Next (Planned Steps)

### 2️⃣ UTF-8 Decoding (`utf8.h` / `utf8.c`)

**Why next?**

- Sinhala identifiers (`\u0D80–\u0DFF`)
- Zero-width joiner / space (`\u200D`, `\u200B`)
- `char` ≠ Unicode character

You will implement:

- Read next UTF-8 code point
- Advance by 1–4 bytes
- Return Unicode scalar value (`uint32_t`)

This enables:

- Correct identifier lexing
- Keyword matching
- Accurate column tracking

---

### 3️⃣ `lexer.h`

Defines:

- `Lexer` struct
- Public lexer functions
- Interface only (no logic)

---

### 4️⃣ `lexer.c`

Implements:

- `advance()`
- number lexing
- identifier/keyword lexing
- string lexing
- operators (`==`, `!=`, `<=`, `->`, etc.)
- newline handling
- error reporting

This will closely mirror your Python `lexer.py`, but explicitly.

---

### 5️⃣ Parser → AST → LLVM (later)

Not started yet.

---

## 🧠 Design Principles Being Followed

- **Byte-based indexing** (UTF-8 correctness)
- **No premature allocation**
- **Explicit ownership**
- **C as a systems language, not Python-with-semicolons**
- **Readable over clever**

---

# 🧾 CONTINUATION PROMPT (SAVE THIS)

You can paste the following into a new chat verbatim:

---

**Prompt to new AI:**

> I am the creator of the Ceylonicus programming language.  
> I am rewriting it from a Python interpreter into a compiled language using **C as the frontend and LLVM as the backend**.
> 
> Current status:
> 
> - Platform: Windows, GCC
> - Source files must be UTF-8
> - We chose the “traditional compiler” route (C + LLVM)
> - I prefer **mentoring-style explanations**
> - I do **not** want full code snippets unless I explicitly ask
> 
> What is already done:
> 
> - `token.h` is complete:
>     
>     - include guards
>     - `TokenType` enum
>     - `Position` struct (byte index, line, column)
>     - `StrSlice` (pointer + length into UTF-8 source)
>     - `TokenValue` union (int, float, string slice)
>     - `Token` struct (type, value, start, end)

> - This mirrors my original Python `lexer.py`
>     
> Next step should be:
> 
> - UTF-8 decoding utilities (`utf8.h` / `utf8.c`)
> - Then `lexer.h` and `lexer.c`
>     
> Please continue mentoring me from **this exact point**.

---

## Final reassurance

You’ve actually done **the hardest conceptual part already**:

- translating Python’s dynamic model into a clean C data model
    

Everything from here on is _mechanical and incremental_.