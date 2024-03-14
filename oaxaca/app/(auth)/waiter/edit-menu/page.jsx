"use client"

import EditableMenuItem from "./components/editable-menu-card"
import MenuList from "@/app/(home)/menus/components/menu-list"
import MenuFilters from "@/app/(home)/menus/components/menu-filter"
import { useState } from "react"
import EditableMenuList from "./components/editable-menu-list"

export default function EditMenuPage() {

    // Change this later to fetch from DB
    const dummyAllergens = [
        { id: 0, name: 'None' },
        {
          id: 1,
          name: 'Gluten',
        },
        {
          id: 2,
          name: 'Shellfish',
        },
        {
          id: 3,
          name: 'Nuts',
        },
        {
          id: 4,
          name: 'Dairy',
        },
      ];

    const [selectedFilters, setSelectedFilters] = useState([]);


    return (
        <main className="flex gap-8 p-4 px-10">
            <EditableMenuList
                selectedFilters={selectedFilters}
                dummyAllergens={dummyAllergens}
            />
            <MenuFilters
                filters={dummyAllergens.slice(1).map((a) => ({
                    id: a.id,
                    name: `${a.name}-free`,
                }))}
                selectedFilters={selectedFilters}
                setSelectedFilters={setSelectedFilters}
            />
        </main>
    )
}