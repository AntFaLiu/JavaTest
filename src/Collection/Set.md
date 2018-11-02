## Set 的抽象实现类 AbstractSet
    AbstractSet 和 AbstractList 一样，都继承自 AbstractCollection 类，并且实现了各自的行为接口，AbstractSet 实现的是 Set。
    AbstractSet 就如下几个方法，我们来看一下。
    AbstractSet()构造方法
    equals 方法。Set 集合的 equals(e)只有当 e 是自身，或者是一个Collection 并且满足containsAll(e)才会返回 true
    hashCode Set 集合的 hashCode 值是集合中所有非空元素的hashCode 累加和。
    removeAll 操作 Iterator，实现了清空集合的操作。
    可见 AbstractSet 类并没有什么特别的属性和能力，因此我们直接看具体实现类吧。
