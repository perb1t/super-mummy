package com.jwshi.supermummy.widget.selector;

import java.util.List;

/**
 * Created by shijiwei on 2020-08-23.
 *
 * @Desc:
 */
public interface Selector<T> {

    void addOptions(List<T> options);

    T selected();

}
