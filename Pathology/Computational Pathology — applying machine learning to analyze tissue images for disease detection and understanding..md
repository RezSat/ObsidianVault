
# Computational Pathology — Notes

## 0. Core Idea

> Tissue = physical manifestation of underlying biological program  
> Pathology = observing system state through microscopy  
> Computational Pathology = learning mappings:  
> **Image → State / Outcome**

Not medicine.  
**Pattern extraction from noisy biological systems.**

---

## 1. What Are We Actually Working With

### Data Types

- Whole Slide Images (WSI)
    - Gigapixel (100k × 100k)
    - Stored as pyramids (multi-resolution)
    - Formats: `.svs`, `.tiff`, `.ndpi`

- Labels (weak, noisy)
    - Slide-level (e.g. cancer / no cancer)
    - Region-level (rare, expensive)
    - Survival / prognosis

### Core Constraint

> Never get clean labels.

---

## 2. Mental Model

Treat it like reverse engineering:

- See output (image)
- Infer hidden state (disease)

Like:
- binary → behavior
- firmware → signals

Except here:
- tissue → biology

---

## 3. Basic Domain Knowledge (minimum viable)

Do NOT go full medicine.

Learn only:

- Cell, nucleus, cytoplasm
- Tissue organization
- H&E staining:
    - Hematoxylin → nuclei (blue)
    - Eosin → cytoplasm (pink)
        

Key idea:
> Color = information channel

---

## 4. Pipeline (end-to-end)

### Step 1 — Load WSI

- Use OpenSlide
- Understand pyramid levels
- Work at multiple magnifications

### Step 2 — Tissue Detection

- Remove background (white regions)
- Simple thresholding works

### Step 3 — Patch Extraction

- Split into tiles (e.g. 256×256)
- Sampling strategies:
    - grid
    - random
    - tissue-aware

Constraint:
> Full slide won’t fit in memory.

---

### Step 4 — Feature Extraction

Options:

- CNN (ResNet, EfficientNet)
- Self-supervised models
- Pretrained histology models

Output:
- embedding vector per patch

---

### Step 5 — Aggregation (critical problem)

So we  have:
- many patches
- one label
    

This is:

> Multiple Instance Learning (MIL)

Methods:

- mean / max pooling (baseline)
    
- attention-based MIL
    
- transformer-based aggregation
    

---

### Step 6 — Prediction

Tasks:

- classification (cancer / no cancer)
    
- grading
    
- survival prediction (regression)
    

---

### Step 7 — Interpretation

Must have:

- attention heatmaps
    
- saliency maps
    

Doctors don’t trust black boxes.

---

## 5. Core Problems

### Weak Supervision

- Label at slide level
    
- Signal at patch level
    

=> credit assignment problem

---

### Data Variability

- staining differences
    
- scanners
    
- hospitals
    

=> domain shift

---

### Scale

- 1 slide = millions of patches
    

=> sampling, batching, caching

---

### Noise

- mislabeled data
    
- ambiguous regions
    

=> robustness required

---

## 6. Important Techniques

### Multiple Instance Learning (MIL)

Key idea:

> Bag of instances → one label

must understand:

- attention MIL
- instance weighting

---

### Self-Supervised Learning

Because labels are limited:

- contrastive learning
    
- masked modeling
    

---

### Color Normalization

- Macenko method
    
- Reinhard normalization
    

Important for generalization.

---

### Data Augmentation

- color jitter
    
- rotation
    
- stain augmentation
    

---

## 7. Evaluation

Don’t be naive.

Use:

- AUC (classification)
    
- F1 score
    
- Concordance index (survival)
    

Be careful:

> Data leakage across patients

---

## 8. Engineering Problems

- Efficient patch extraction
    
- Disk I/O bottlenecks
    
- GPU utilization
    
- Dataset caching
    
- Parallel pipelines
    


---

## 9. Datasets (start here)

- CAMELYON16 / 17 (metastasis detection)
    
- TCGA (multi-modal data)
    
- PANDA (prostate cancer grading)
    

---

## 10. First Projects

### Project 1 — Basic Pipeline

- Load WSI
    
- Extract patches
    
- Train CNN on patches
    
- Aggregate (mean pooling)
    

---

### Project 2 — MIL Model

- Implement attention MIL
    
- Compare with baseline
    

---

### Project 3 — Visualization

- Highlight important regions
    
- Generate heatmaps
    

---

### Project 4 — Optimization

- Speed up patch pipeline
    
- Reduce memory
    
- Benchmark
    

---

## 11. Research Directions

- Multi-modal (image + genomics)
    
- Survival prediction
    
- Foundation models for pathology
    
- Weak supervision improvements
    

---

## 12. How to Think

Do NOT think:

> "This is a medical problem"

Think:

> "This is a high-noise, weak-label, large-scale vision system"

---

## 13. Stack

- Python
    
- PyTorch
    
- OpenSlide
    
- OpenCV
    
- NumPy
    

Optional:

- Dask (large data)
    
- Ray (parallelism)
    

---

## 14. What Actually Matters

Not:

- fancy architectures
    

But:

- data handling
    
- sampling strategy
    
- label quality
    
- interpretation
    

---

## 15. Guiding Principle

> Better data > better model  
> Better aggregation > deeper CNN  
> Better understanding > more layers

---

## 16. End Goal

> Given a slide, I can build a system that extracts meaningful biological signal from it.

Not:

> I trained ResNet-50 and got 92%.