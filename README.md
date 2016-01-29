# JMLLib (Java Machine Learning Library)
A Java library for building supervised learning models - regression & classification. This library is currently in progress and when complete, it will support following models -

1. Linear Regression

2. Logistic Regression

3. SVM

4. Decision Tree, Random Forest

5. Artificial Neural Network


## Installation
git clone git@github.com:tkrishp/JMLLib.git

`mvn clean install`

## Usage

1. Linear Regression
```
  Dataset X = Dataset.parser("path to csv");
  RVector y = RVector.parser("path to file");
  LinearRegression lr = new LinearRegression(X, y);
  RVector theeta = lr.train();
  Dataset newX = Dataset.parsert("path to csv");
  RVector predictions = lr.predict(newX);
```

