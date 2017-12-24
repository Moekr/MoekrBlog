package com.moekr.blog.logic.service;

import com.moekr.blog.data.dao.TagDAO;
import com.moekr.blog.data.entity.Tag;
import com.moekr.blog.logic.vo.TagVO;
import com.moekr.blog.util.ToolKit;
import com.moekr.blog.web.dto.TagDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "tag")
public class TagService {
    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$");

    private final TagDAO tagDAO;

    @Autowired
    public TagService(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Cacheable(key = "'tagList'")
    public List<TagVO> getTags(){
        return tagDAO.findAll().stream().map(TagVO::new).collect(Collectors.toList());
    }

    @Cacheable(key = "#tagId")
    public TagVO getTag(String tagId){
        Tag tag = tagDAO.findById(tagId);
        ToolKit.assertNotNull(tagId, tag);
        return new TagVO(tag);
    }

    @Transactional
    @Caching(put = @CachePut(key = "#tagId"), evict = @CacheEvict(key = "'tagList'"))
    public TagVO updateTag(String tagId, TagDTO tagDTO){
        Tag tag = tagDAO.findById(tagId);
        if(tag == null){
            ToolKit.assertPattern(tagId, PATTERN);
            tag = new Tag();
            tag.setId(tagId);
        }
        BeanUtils.copyProperties(tagDTO, tag);
        return new TagVO(tagDAO.save(tag));
    }

    @Transactional
    @Caching(evict = {@CacheEvict(key = "#tagId"), @CacheEvict(key = "'tagList'")})
    public void deleteTag(String tagId){
        Tag tag = tagDAO.findById(tagId);
        ToolKit.assertNotNull(tagId, tag);
        tagDAO.delete(tag);
    }
}
