package com.avaje.ebeaninternal.server.deploy;

import javax.persistence.CascadeType;

/**
 * Persist info for determining if save or delete should be performed.
 * <p>
 * This is set to associated Beans, Table joins and List.
 * </p>
 */
public class BeanCascadeInfo {

    /**
     * should delete cascade.
     */
    boolean delete;
    
    /**
     * Should save cascade.
     */
    boolean save;

    /**
     * Set the raw deployment attribute.
     */
    public void setAttribute(String attr) {
        if (attr == null){
            return;
        }
        attr = attr.toLowerCase();
        delete = (attr.contains("delete"));
        if (!delete){
            // same as EJB3 remove
            delete = (attr.contains("remove"));
        }
        save = (attr.contains("save"));
        if (!save){
            // same as EJB3 persist
            save = (attr.contains("persist"));
        }
        
        if (attr.contains("all")){
            delete = true;
            save = true;
        }
    }

    public void setTypes(CascadeType[] types) {
        for (int i = 0; i < types.length; i++) {
            setType(types[i]);
        }
    }
    
    private void setType(CascadeType type) {
        if (type.equals(CascadeType.ALL)){
            save = true;
            delete = true;
        }
        if (type.equals(CascadeType.REMOVE)){
            delete = true;
        }
        if (type.equals(CascadeType.PERSIST)){
            save = true;
        }
        if (type.equals(CascadeType.MERGE)){
            save = true;
        }
    }
    
    /**
     * Return true if delete should cascade.
     */
    public boolean isDelete() {
        return delete;
    }
    /**
     * Set to true if delete should cascade.
     */
    public void setDelete(boolean isDelete) {
        this.delete = isDelete;
    }
    /**
     * Return true if save should cascade.
     */
    public boolean isSave() {
        return save;
    }
    	
    /**
     * Set to true if save should cascade.
     */
    public void setSave(boolean isUpdate) {
        this.save = isUpdate;
    }

}
