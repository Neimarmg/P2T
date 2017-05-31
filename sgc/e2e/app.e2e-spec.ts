import { SGCPage } from './app.po';

describe('sgc App', () => {
  let page: SGCPage;

  beforeEach(() => {
    page = new SGCPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
