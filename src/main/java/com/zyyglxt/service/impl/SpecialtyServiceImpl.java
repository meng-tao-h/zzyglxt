package com.zyyglxt.service.impl;

import com.zyyglxt.dao.HospSpecialtyRefDOMapper;
import com.zyyglxt.dao.SpecialtyDOMapper;
import com.zyyglxt.dataobject.HospSpecialtyRefDO;
import com.zyyglxt.dataobject.HospSpecialtyRefDOKey;
import com.zyyglxt.dataobject.SpecialtyDO;
import com.zyyglxt.dataobject.SpecialtyDOKey;
import com.zyyglxt.dto.SpecialtyDto;
import com.zyyglxt.service.ISpecialtyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author qjc
 * @version 1.0
 * @date 2020/10/29 17:32
 */
@Service
public class SpecialtyServiceImpl implements ISpecialtyService {

    @Resource
    SpecialtyDOMapper specialtyDOMapper;
    @Resource
    HospSpecialtyRefDOMapper hospSpecialtyRefDOMapper;
    @Resource
    SpecialtyDO specialtyDO;
    @Resource
    HospSpecialtyRefDO hospSpecialtyRefDO;

    /*增加科室，同时也将科室与医院记录插入*/
    @Override
    public void addSpecialty(SpecialtyDto specialtyDto) {
        specialtyDO = specialtyDto;
        specialtyDO.setItemcode(UUID.randomUUID().toString());

        hospSpecialtyRefDO.setItemcode(UUID.randomUUID().toString());
        hospSpecialtyRefDO.setHospitalCode(specialtyDto.getHospitalCode());
        hospSpecialtyRefDO.setSpecialtyCode(specialtyDto.getHospitalCode());
        hospSpecialtyRefDO.setCreater(specialtyDto.getCreater());
        hospSpecialtyRefDO.setItemcreateat(specialtyDto.getItemcreateat());
        hospSpecialtyRefDO.setUpdater(specialtyDto.getUpdater());
        hospSpecialtyRefDO.setItemupdateat(specialtyDto.getItemupdateat());

        specialtyDOMapper.insertSelective(specialtyDO);
        hospSpecialtyRefDOMapper.insertSelective(hospSpecialtyRefDO);
    }

    /*更新科室信息，同步更新医院科室关系表*/
    @Override
    public void updateSpecialty(SpecialtyDto specialtyDto) {
        specialtyDO = specialtyDto;

        hospSpecialtyRefDO.setItemcode(UUID.randomUUID().toString());
        hospSpecialtyRefDO.setHospitalCode(specialtyDto.getHospitalCode());
        hospSpecialtyRefDO.setSpecialtyCode(specialtyDto.getHospitalCode());
        hospSpecialtyRefDO.setCreater(specialtyDto.getCreater());
        hospSpecialtyRefDO.setItemcreateat(specialtyDto.getItemcreateat());
        hospSpecialtyRefDO.setUpdater(specialtyDto.getUpdater());
        hospSpecialtyRefDO.setItemupdateat(specialtyDto.getItemupdateat());

        specialtyDOMapper.updateByPrimaryKeySelective(specialtyDO);
        hospSpecialtyRefDOMapper.updateByPrimaryKeySelective(hospSpecialtyRefDO);
    }

    /*删除科室记录，包括科室表和关系表*/
    @Override
    public void deleteSpecialty(SpecialtyDto specialtyDto) {
        SpecialtyDOKey specialtyDOKey = specialtyDto;
        HospSpecialtyRefDOKey hospSpecialtyRefDOKey = new HospSpecialtyRefDOKey();
        hospSpecialtyRefDOKey.setItemid(specialtyDto.getItemid());
        hospSpecialtyRefDOKey.setItemcode(specialtyDto.getItemcode());

        hospSpecialtyRefDOMapper.deleteByPrimaryKey(hospSpecialtyRefDOKey);
        specialtyDOMapper.deleteByPrimaryKey(specialtyDOKey);
    }

    /*查询所有科室*/
    @Override
    public List<SpecialtyDO> selectAllSpecialty() {
        return specialtyDOMapper.selectAllSpecialty();
    }

    /*
    搜索关键字，包括专科名，专科介绍，专科所在省市县，手动输入地址，专科所属医院名
     */
    @Override
    public List<SpecialtyDO> searchSpecialty(String keyWord) {
        return specialtyDOMapper.searchSpecialty(keyWord);
    }

    /*查询前五条记录*/
    @Override
    public List<SpecialtyDO> top5Specialty() {
        return specialtyDOMapper.top5Specialty();
    }


}
