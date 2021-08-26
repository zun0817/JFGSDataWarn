package com.ztzb.data.model.data;


import java.io.Serializable;
import java.util.List;

public class SafeProjectBean implements Serializable {

    private Boolean expanded;
    private Integer level;
    private String id;
    private String text;
    private String type;
    private Boolean leaf;
    private String iconCls;
    private Integer nodeID;
    private List<ChildrenDTOXX> children;

    public Boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getNodeID() {
        return nodeID;
    }

    public void setNodeID(Integer nodeID) {
        this.nodeID = nodeID;
    }

    public List<ChildrenDTOXX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenDTOXX> children) {
        this.children = children;
    }

    public static class ChildrenDTOXX implements Serializable {
        private Boolean expanded;
        private Integer level;
        private String id;
        private String text;
        private String ParentIDStr;
        private Boolean leaf;
        private String type;
        private String iconCls;
        private Integer nodeID;
        private List<ChildrenDTOX> children;

        public Boolean isExpanded() {
            return expanded;
        }

        public void setExpanded(Boolean expanded) {
            this.expanded = expanded;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getParentIDStr() {
            return ParentIDStr;
        }

        public void setParentIDStr(String ParentIDStr) {
            this.ParentIDStr = ParentIDStr;
        }

        public Boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(Boolean leaf) {
            this.leaf = leaf;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIconCls() {
            return iconCls;
        }

        public void setIconCls(String iconCls) {
            this.iconCls = iconCls;
        }

        public Integer getNodeID() {
            return nodeID;
        }

        public void setNodeID(Integer nodeID) {
            this.nodeID = nodeID;
        }

        public List<ChildrenDTOX> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenDTOX> children) {
            this.children = children;
        }

        public static class ChildrenDTOX implements Serializable {
            private Boolean expanded;
            private Integer level;
            private String id;
            private String text;
            private String ParentIDStr;
            private Boolean leaf;
            private String type;
            private String iconCls;
            private Integer nodeID;
            private List<ChildrenDTO> children;

            public Boolean isExpanded() {
                return expanded;
            }

            public void setExpanded(Boolean expanded) {
                this.expanded = expanded;
            }

            public Integer getLevel() {
                return level;
            }

            public void setLevel(Integer level) {
                this.level = level;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getParentIDStr() {
                return ParentIDStr;
            }

            public void setParentIDStr(String ParentIDStr) {
                this.ParentIDStr = ParentIDStr;
            }

            public Boolean isLeaf() {
                return leaf;
            }

            public void setLeaf(Boolean leaf) {
                this.leaf = leaf;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getIconCls() {
                return iconCls;
            }

            public void setIconCls(String iconCls) {
                this.iconCls = iconCls;
            }

            public Integer getNodeID() {
                return nodeID;
            }

            public void setNodeID(Integer nodeID) {
                this.nodeID = nodeID;
            }

            public List<ChildrenDTO> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenDTO> children) {
                this.children = children;
            }

            public static class ChildrenDTO implements Serializable {
                private Boolean expanded;
                private Integer level;
                private String id;
                private String text;
                private String ParentIDStr;
                private Boolean leaf;
                private String type;
                private String iconCls;
                private Integer nodeID;

                public Boolean isExpanded() {
                    return expanded;
                }

                public void setExpanded(Boolean expanded) {
                    this.expanded = expanded;
                }

                public Integer getLevel() {
                    return level;
                }

                public void setLevel(Integer level) {
                    this.level = level;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getParentIDStr() {
                    return ParentIDStr;
                }

                public void setParentIDStr(String ParentIDStr) {
                    this.ParentIDStr = ParentIDStr;
                }

                public Boolean isLeaf() {
                    return leaf;
                }

                public void setLeaf(Boolean leaf) {
                    this.leaf = leaf;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getIconCls() {
                    return iconCls;
                }

                public void setIconCls(String iconCls) {
                    this.iconCls = iconCls;
                }

                public Integer getNodeID() {
                    return nodeID;
                }

                public void setNodeID(Integer nodeID) {
                    this.nodeID = nodeID;
                }
            }
        }
    }
}
