package com.example.gerardodwyer.tabletopcompanion.model;

public class ItemDisplay {

    private String Id;
    private String Item;
    private String LootedFrom;
    private String Lore;
    private String Description;
    private Boolean Uniqe;
    private String Type;

    public ItemDisplay(String id, String item, String lootedFrom, String lore, String description, Boolean uniqe, String type) {
        Id = id;
        Item = item;
        LootedFrom = lootedFrom;
        Lore = lore;
        Description = description;
        Uniqe = uniqe;
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

    public Boolean getUniqe() {
        return Uniqe;
    }

    public void setUniqe(Boolean uniqe) {
        Uniqe = uniqe;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
