import { AppUsuarioPage } from './app.po';

describe('app-usuario App', () => {
  let page: AppUsuarioPage;

  beforeEach(() => {
    page = new AppUsuarioPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
