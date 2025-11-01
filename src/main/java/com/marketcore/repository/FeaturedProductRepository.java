package com.marketcore.repository;

import com.marketcore.model.FeaturedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeaturedProductRepository extends JpaRepository<FeaturedProduct, Long> {

    @Query("SELECT fp FROM FeaturedProduct fp WHERE fp.sectionName = :sectionName AND fp.isActive = true ORDER BY fp.displayOrder ASC")
    List<FeaturedProduct> findActiveFeaturedProductsBySection(@Param("sectionName") String sectionName);

    @Query("SELECT fp FROM FeaturedProduct fp WHERE fp.sectionName = :sectionName ORDER BY fp.displayOrder ASC")
    List<FeaturedProduct> findAllFeaturedProductsBySection(@Param("sectionName") String sectionName);

    boolean existsByProductIdAndSectionName(Long productId, String sectionName);

    void deleteByProductIdAndSectionName(Long productId, String sectionName);
}
