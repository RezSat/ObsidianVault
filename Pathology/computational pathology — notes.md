## idea

tissue = physical output of biological process  
pathology = inspection of that output  
computational pathology = learn mapping from image → latent biological state  

this is not medicine  
this is signal extraction from noisy visual data  

---

## data

whole slide images (wsi)
- gigapixel scale (10^9 pixels)
- multi-resolution pyramid
- formats: svs, tiff, ndpi

labels
- slide-level (weak)
- region-level (rare)
- patient-level outcomes (survival)

problem:
labels are coarse, signal is local  

---

## representation

image != natural image  
color carries domain meaning  

H&E staining:
- hematoxylin → nuclei (blue)
- eosin → cytoplasm (pink)

color distribution is part of signal  
cannot treat as generic RGB  

---

## constraints

- full slide cannot fit in memory
- label resolution mismatch (slide vs patch)
- heavy noise (label + acquisition)
- domain shift across hospitals

---

## pipeline

### load

use openslide  
multi-scale access  
work at different magnifications  

---

### tissue detection

remove background  
white regions ≈ no tissue  

simple threshold works  

---

### patch extraction

tile into patches (e.g. 256x256)

strategies:
- grid
- random
- tissue-aware

sampling matters  

---

### feature extraction

cnn backbone:
- resnet
- efficientnet

or self-supervised encoder  

output:
embedding per patch  

---

### aggregation

many patches → one label  

this is MIL (multiple instance learning)

methods:
- mean pooling
- max pooling
- attention

key problem:
which patches matter  

---

### prediction

tasks:
- classification
- grading
- survival regression

---

### interpretation

required for trust  

methods:
- attention maps
- saliency

model must localize signal  

---

## core problems

weak supervision
- no patch labels
- credit assignment problem  

domain shift
- staining variation
- scanner differences  

scale
- millions of patches per slide  

noise
- label noise
- biological ambiguity  

---

## techniques

multiple instance learning
- bag of instances
- attention weighting  

self-supervised learning
- learn representations without labels  

color normalization
- macenko
- reinhard  

augmentation
- rotation
- color jitter
- stain perturbation  

---

## evaluation

classification:
- auc
- f1

survival:
- concordance index  

important:
no patient overlap between splits  

---

## engineering

- patch extraction speed
- disk I/O bottleneck
- caching
- batching
- gpu utilization

most pipelines fail here  

---

## datasets

- camelyon (metastasis)
- tcga (multi-modal)
- panda (grading)

---

## mental model

image = observation  
disease = hidden variable  

goal:
learn mapping  
f: image → latent state  

---

## research direction

- image + genomics (multi-modal)
- survival modeling
- foundation models for histology
- weak supervision improvements  

---

## principles

better data > better model  
aggregation > backbone depth  
interpretability required  

---

## end

problem is not classification  
problem is extracting biological signal from large, noisy, weakly labeled images 