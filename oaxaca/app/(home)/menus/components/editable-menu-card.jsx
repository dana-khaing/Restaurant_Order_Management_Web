import React, { useState } from 'react';

const EditableMenuItem = ({ item, onSave, onCancel }) => {
    const [editedItem, setEditedItem] = useState(item);

  const handleNameChange = (event) => {
    setName(event.target.value);
  };
};