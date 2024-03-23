'use client';

import { createContext, useState } from 'react';

export const AuthContext = createContext({
  user: null,
  loginUser: (user) => {},
});

export default function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  const loginUser = (user) => setUser(user);
  const logoutUser = () => setUser(null);

  return (
    <AuthContext.Provider value={{ user, loginUser, logoutUser }}>
      {children}
    </AuthContext.Provider>
  );
}
