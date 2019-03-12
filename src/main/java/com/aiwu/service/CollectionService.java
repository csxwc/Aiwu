package com.aiwu.service;

import com.aiwu.bean.Collection;
import com.aiwu.bean.House;
import com.aiwu.bean.Rating;
import com.aiwu.repository.CollectionRepository;
import com.aiwu.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private HouseService houseService;

    public List<Map<String, Object>> getCollectionsByUserId(Integer userId) {
        List<Map<String, Object>> finallist = new ArrayList<>();
        List<Collection> collections = collectionRepository.findAllByPersonId(userId);
        for(int i = 0; i < collections.size(); i++)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            //id,room_id,housename,city,type,price
            Collection collection = collections.get(i);
            House house = houseService.findById(collection.getRoom_id());
            map.put("id", collection.getId());
            map.put("room_id", collection.getRoom_id());
            map.put("title", house.getName());
            map.put("city", house.getCity());
            map.put("type", house.getType());
            map.put("price", house.getPrice());
            finallist.add(map);
        }
        return finallist;
    }

    public Collection getCollectionById(Integer id) {
        return collectionRepository.findById(id);
    }

    public Boolean isCollected(Integer userId, Integer houseId) {

        Collection collection = collectionRepository.findByPersonIdAndRoomId(userId, houseId);
        if (collection == null)
            return false;
        else
            return true;

    }

    @Transactional
    public void addCollection(Integer personId, Integer roomId) {
        Collection collection = new Collection();
        collection.setPerson_id(personId);
        collection.setRoom_id(roomId);
        System.out.println(collection);
        collectionRepository.save(collection);

    }

    @Transactional
    public Boolean deleteCollection(Integer id) {

        if (collectionRepository.deleteById(id) == 1)
            return true;
        else
            return false;
    }

}
