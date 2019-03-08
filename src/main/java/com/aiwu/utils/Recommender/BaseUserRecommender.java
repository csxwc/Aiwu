package com.aiwu.utils.Recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseUserRecommender {

    public static void main(String[] args) throws IOException, TasteException {

        // 基于用户的推荐

        //准备数据 这里是电影评分数据
        File file = new File("/home/hong/IdeaProjects/aiwu/ml-20m/ratings.csv");
        //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
        DataModel dataModel = new FileDataModel(file);
        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
        UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
        //计算最近邻域，邻居有两种算法，基于固定数量的邻居和基于相似度的邻居，这里使用基于固定数量的邻居
        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);
        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于用户的协同过滤推荐
        Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);
        //给用户ID等于5的用户推荐10部电影
        List<RecommendedItem> recommendedItemList = recommender.recommend(10, 20);
        //打印推荐的结果
        System.out.println("使用基于用户的协同过滤算法");
        System.out.println("为用户10推荐20个商品");
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(recommendedItem);
        }

        // 基于物品的推荐

        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
        ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于物品的协同过滤推荐
        GenericItemBasedRecommender itemRecommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
        //给用户ID等于5的用户推荐10个与2398相似的商品
        List<RecommendedItem> itemRecommendedItemList = itemRecommender.recommendedBecause(5, 34, 20);
        long start = System.currentTimeMillis();
        //打印推荐的结果
        System.out.println("使用基于物品的协同过滤算法");
        System.out.println("根据用户5当前浏览的商品34，推荐20个相似的商品");
        for (RecommendedItem itemRecommendedItem : itemRecommendedItemList) {
            System.out.println(itemRecommendedItem);
        }
        System.out.println(System.currentTimeMillis() -start);

//        //准备数据 这里是电影评分数据
//        File file = new File("/home/hong/IdeaProjects/aiwu/ml-20m/ratings.csv");
//        //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
//        DataModel dataModel = new FileDataModel(file);
//        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
//        ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
//        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于物品的协同过滤推荐
//        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
//        //给用户ID等于5的用户推荐10个与2398相似的商品
//        List<RecommendedItem> recommendedItemList = recommender.recommendedBecause(5, 2509, 10);
//        //打印推荐的结果
//        System.out.println("使用基于物品的协同过滤算法");
//        System.out.println("根据用户5当前浏览的商品2509，推荐10个相似的商品");
//        for (RecommendedItem recommendedItem : recommendedItemList) {
//            System.out.println(recommendedItem);
//        }
//        long start = System.currentTimeMillis();
//        recommendedItemList = recommender.recommendedBecause(5, 34, 10);
//        //打印推荐的结果
//        System.out.println("使用基于物品的协同过滤算法");
//        System.out.println("根据用户5当前浏览的商品34，推荐10个相似的商品");
//        for (RecommendedItem recommendedItem : recommendedItemList) {
//            System.out.println(recommendedItem);
//        }
//        System.out.println(System.currentTimeMillis() -start);

    }

}
