Works:

```
xjc a.xsd -b a-relative.xjb
```

Does not work:

```
xjc -catalog catalog.cat http://www.ab.org/a.xsd 
```

Does not work:

```
xjc -catalog catalog.cat http://www.ab.org/a.xsd -b a-absolute.xjb
```

Does not work:

```
xjc -catalog catalog.cat http://www.ab.org/a.xsd -b a-relative.xjb
```