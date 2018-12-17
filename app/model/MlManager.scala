package model

import model.helpers.{FeatureHelper}
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification._
import org.apache.spark.sql.DataFrame

object ModelType {

  val LOGISTIC_REGRESSION   = "LOGISTIC_REGRESSION"
  val DECISION_TREE         = "DECISION_TREE"
  val RANDOM_FOREST         = "RANDOM_FOREST"
  val GRADIENT_BOOSTED_TREE = "GRADIENT_BOOSTED_TREE"
  val PERCEPTRON            = "PERCEPTRON"
  val LINEAR_SVM            = "LINEAR_SVM"
  val ONE_VS_REST           = "ONE_VS_REST"
  val NAIVE_BAYES           = "NAIVE_BAYES"

}

object FeatureType {

  val CATEGORICAL = "CATEGORIAL"
  val CONTINUOS   = "CONTINUOS"
}

trait  WModel[T]{
  val model: T
}

case class WLogisticRegression(override val model:LogisticRegression = new LogisticRegression()) extends WModel[LogisticRegression]
case class WDecisionTreeClassifier(override val model: DecisionTreeClassifier = new DecisionTreeClassifier()) extends WModel[DecisionTreeClassifier]
case class WRandomForestClassifier(override val model: RandomForestClassifier = new RandomForestClassifier()) extends WModel[RandomForestClassifier]
case class WGBTClassifier(override val model: GBTClassifier = new GBTClassifier()) extends WModel[GBTClassifier]
case class WMultilayerPerceptronClassifier(override val model: MultilayerPerceptronClassifier = new MultilayerPerceptronClassifier()) extends WModel[MultilayerPerceptronClassifier]
case class WLinearSVC(override val model: LinearSVC = new LinearSVC()) extends WModel[LinearSVC]
case class WOneVsRest(override val model: OneVsRest = new OneVsRest()) extends WModel[OneVsRest]
case class WNaiveBayes(override val model: NaiveBayes = new NaiveBayes()) extends WModel[NaiveBayes]


case class Feature(name: String, _type: String = FeatureType.CATEGORICAL)

case class MlModelFactory() {

  def get(modelType: String) = modelType match {

    case ModelType.LOGISTIC_REGRESSION => WLogisticRegression()
    case ModelType.DECISION_TREE => WDecisionTreeClassifier()
    case ModelType.RANDOM_FOREST => WRandomForestClassifier()
    case ModelType.GRADIENT_BOOSTED_TREE => WGBTClassifier()
    case ModelType.PERCEPTRON => WMultilayerPerceptronClassifier()
    case ModelType.LINEAR_SVM => WLinearSVC()
    case ModelType.ONE_VS_REST => WOneVsRest()
    case ModelType.NAIVE_BAYES => WNaiveBayes()

  }
}


case class MlManager () extends Serializable with FeatureHelper{

  def createFeaturePipeline(features: Array[Feature]): Pipeline = {



    new Pipeline()
  }

  def createPipelineModel(modelType: String, featuresDF: DataFrame): Pipeline = {

    val featurePipeline = createFeaturePipeline(Array())

    val mlModel = MlModelFactory().get(modelType).model

    new Pipeline().setStages(featurePipeline.getStages ++ Array(mlModel))

  }
}

