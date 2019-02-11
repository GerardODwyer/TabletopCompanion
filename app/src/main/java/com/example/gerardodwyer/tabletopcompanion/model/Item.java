package com.example.gerardodwyer.tabletopcompanion.model;

public class Item {

    private String Id;
    private String Item;
    private String LootedFrom;
    private String Lore;
    private String Description;
    private String Unique;
    private String Type;

    public Item(String id, String item, String lootedFrom, String lore, String description, String unique, String type) {
        Id = id;
        Item = item;
        LootedFrom = lootedFrom;
        Lore = lore;
        Description = description;
        Unique = unique;
        Type = type;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getLootedFrom() {
        return LootedFrom;
    }

    public void setLootedFrom(String lootedFrom) {
        LootedFrom = lootedFrom;
    }

    public String getLore() {
        return Lore;
    }

    public void setLore(String lore) {
        Lore = lore;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUnique() {
        return Unique;
    }

    public void setUnique(String unique) {
        Unique = unique;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
