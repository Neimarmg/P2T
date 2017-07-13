import { SgcAppPage } from './app.po';

describe('sgc-app App', () => {
  let page: SgcAppPage;

  beforeEach(() => {
    page = new SgcAppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
