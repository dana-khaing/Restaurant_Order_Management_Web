const Items = [
  { label: 'MENU', link: '/menus' },
  { label: 'ABOUT US', link: '/' },
  { label: 'CONTACT US', link: '/' },
];

export const NavList = () => {
  return (
    <div className='flex justify-center space-x-14'>
      {Items.map((item, index) => (
        <a key={index} href={item.link} className='font-semibold '>
          {item.label}
        </a>
      ))}
    </div>
  );
};
