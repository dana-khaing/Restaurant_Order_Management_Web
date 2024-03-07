import React, { useState } from 'react';

const EditableMenuCard = ({ index, name: initialName, diet: initialDiet, onSave, onCancel }) => {
  const [name, setName] = useState(initialName);
  const [diet, setDiet] = useState(initialDiet);

  const handleNameChange = (event) => {
    setName(event.target.value);
  };
};