import { SgcPage } from './app.po';

describe('sgc App', () => {
  let page: SgcPage;

  beforeEach(() => {
    page = new SgcPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
