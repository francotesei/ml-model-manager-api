package model.helpers

import org.apache.spark.ml.feature.{OneHotEncoder, OneHotEncoderEstimator, QuantileDiscretizer, StringIndexer}
import org.apache.spark.sql.DataFrame

trait FeatureHelper {


  def indexFeatures( featureDFs: Map[String, DataFrame], featureCols: Array[String],
                     indexedCols: Array[String]): Array[StringIndexer] =
    featureCols
      .zip(indexedCols)
      .map { case (name, index) =>
        new StringIndexer().setInputCol(name).setOutputCol(index).setHandleInvalid("skip")
      }



  /*
/**
  * @param featureDFs
  * @param featureCols
  * @param oheCols
  * @return
  */


def ohEncodeFeatures( featureDFs: Map[String, DataFrame], featureCols: Array[String],
                      oheCols: Array[String]): Array[OneHotEncoderEstimator] =
  featureCols
    .zip(oheCols)
    .map { case (name, ohe) =>
      new OneHotEncoderEstimator().setInputCol(name).setOutputCol(ohe)
    }

*/
  /**
    *
    * @param featureDFs
    * @param featureCols
    * @param dzeCols
    * @param numBuckets
    * @return
    */

  def discretizeFeatures( featureDFs: Map[String, DataFrame], featureCols: Array[String],
                          dzeCols: Array[String], numBuckets: Int = 255): Array[QuantileDiscretizer] =
    featureCols
      .zip(dzeCols)
      .map { case (name, dze) =>
        new QuantileDiscretizer().setInputCol(name).setOutputCol(dze)
          .setNumBuckets(numBuckets)
      }

}
