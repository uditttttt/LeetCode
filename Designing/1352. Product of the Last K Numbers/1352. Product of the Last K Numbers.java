class ProductOfNumbers {
  ArrayList<Integer> t;

  public ProductOfNumbers() {
      t = new ArrayList<>();
  }

  public void add(int num) {
      t.add(num);
  }

  public int getProduct(int k) {
      int pro = 1;
      int i = 0;
      int n = t.size() - 1;
      while (k > 0) {
          pro = pro * t.get(n - i);
          i++;
          k--;
      }
      return pro;
  }
}

/**
* Your ProductOfNumbers object will be instantiated and called as such:
* ProductOfNumbers obj = new ProductOfNumbers();
* obj.add(num);
* int param_2 = obj.getProduct(k);
*/