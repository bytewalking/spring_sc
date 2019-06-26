package com.evan.demo1.dao;

import com.evan.demo1.entity.Selection;

import java.util.List;
import java.util.Map;

public interface SelectionDao {
    void insert(List<Selection> seles);

    void delete(int sid, int cid);

    List<Map<String,Object>> selectByStudent(int sid);

    List<Map<String,Object>> selectByCoures(int cid);
}
