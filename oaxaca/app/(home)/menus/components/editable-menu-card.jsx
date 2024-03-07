import React, { useState } from 'react';

const EditableMenuItem = ({ item, onSave, onCancel }) => {
    const [editedItem, setEditedItem] = useState(item);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setEditedItem((prevItem) => ({ ...prevItem, [name]: value }));
      };

    const handleSave = () => {
        onSave(editedItem);
    };
    };