## attention-based multiple instance learning (MIL)

setup:
- slide = bag of patches
- each patch = instance
- label only available for whole slide

goal:
learn which patches contribute to slide-level prediction  

---

## problem

given:
X = {x1, x2, ..., xn}  (patch embeddings)

predict:
y (slide label)

unknown:
which xi are important  

---

## naive approach

mean pooling:
z = (1/n) * sum(xi)

problem:
all patches treated equally  

max pooling:
z = max(xi)

problem:
unstable, ignores most data  

---

## attention MIL

learn weights ai for each patch  

z = sum(ai * xi)

where:
ai = softmax(w^T tanh(V xi))

constraints:
sum(ai) = 1  

---

## interpretation

ai ≈ importance of patch i  

high attention:
model considers region relevant  

low attention:
ignored  

---

## advantages

- handles variable number of patches
- weak supervision compatible
- interpretable via attention map  

---

## limitations

- attention ≠ true importance
- sensitive to feature quality
- may focus on artifacts  

---

## implementation sketch

1. extract patch embeddings (CNN)
2. pass through attention network
3. weighted sum
4. classifier head

---

## key idea

not all patches matter  
learn to focus instead of averaging  