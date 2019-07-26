## Resources:

- A Beginner’s Guide to Optimizing Pandas Code for Speed: https://engineering.upside.com/a-beginners-guide-to-optimizing-pandas-code-for-speed-c09ef2c6a4d6
  - Looping using Numpy.arrary is the fatest, then followed by pandas series and apply.
  - This brings us to a few basic conclusions on optimizing Pandas code:
      1. Avoid loops; they’re slow and, in most common use cases, unnecessary.
      2. If you must loop, use apply(), not iteration functions. 
      3. Vectorization is usually better than scalar operations. Most common operations in Pandas can be vectorized.
      4. Vector operations on NumPy arrays are more efficient than on native Pandas series.
  
