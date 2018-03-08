import { AppUsuariosPage } from './app.po';

describe('app-usuarios App', () => {
  let page: AppUsuariosPage;

  beforeEach(() => {
    page = new AppUsuariosPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
